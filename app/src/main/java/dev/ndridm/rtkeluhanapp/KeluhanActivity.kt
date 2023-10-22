package dev.ndridm.rtkeluhanapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import dev.ndridm.rtkeluhanapp.databinding.ActivityKeluhanBinding
import dev.ndridm.rtkeluhanapp.model.RetrofitClient
import dev.ndridm.rtkeluhanapp.reskeluhan.ResponseKeluhan
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class KeluhanActivity : AppCompatActivity() {

    private lateinit var binding: ActivityKeluhanBinding

    private var nama: String =""
    private var rumah: String =""
    private var rt: String =""
    private var jenis_keluhan: String =""
    private var ket: String =""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKeluhanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener {
            startActivity(Intent(this@KeluhanActivity, MainActivityUser::class.java))
            finish()
        }

        binding.btnLapor.setOnClickListener {
            nama = binding.etNama.text.toString()
            rumah = binding.etRumah.text.toString()
            rt = binding.etRt.text.toString()
            jenis_keluhan = binding.etJenis.text.toString()
            ket = binding.etKet.text.toString()

            when {
                nama == ""->{binding.etNama.error = "Tidak boleh kosong!"}
                rumah == ""->{binding.etRumah.error = "Tidak boleh kosong!"}
                rt == ""->{binding.etRt.error = "Tidak boleh kosong!"}
                jenis_keluhan == "" ->{binding.etJenis.error = "Tidak boleh kosong!"}
                ket == ""->{binding.etKet.error = "Tidak boleh kosong!"}

                else ->{
                    binding.progressBar.visibility = View.VISIBLE
                    createData()
                }
            }
        }
    }

    fun createData(){
        val model = RetrofitClient().postKeluhan()
        model.addKeluhan(
            binding.etNama.text.toString(),
            binding.etRumah.text.toString(),
            binding.etRt.text.toString(),
            binding.etJenis.text.toString(),
            binding.etKet.text.toString()
        ).enqueue(object : Callback<ResponseKeluhan>{
            override fun onResponse(
                call: Call<ResponseKeluhan>,
                response: Response<ResponseKeluhan>
            ) {
                if (response.isSuccessful){
                    Toast.makeText(this@KeluhanActivity, "Terkirim", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this@KeluhanActivity, MainActivityUser::class.java))
                    binding.progressBar.visibility = View.VISIBLE
                    finish()
                }else {
                    binding!!.progressBar.visibility = View.GONE
                    Toast.makeText(this@KeluhanActivity,
                    "Keluhan gagal terkirim!", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ResponseKeluhan>, t: Throwable) {
                Log.e("Pesan error", t.message.toString())
            }
        })
    }
}