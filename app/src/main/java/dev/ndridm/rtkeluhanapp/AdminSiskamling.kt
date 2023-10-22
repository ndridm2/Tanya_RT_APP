package dev.ndridm.rtkeluhanapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import dev.ndridm.rtkeluhanapp.databinding.ActivityAdminKerbakBinding
import dev.ndridm.rtkeluhanapp.databinding.ActivityAdminSiskamlingBinding
import dev.ndridm.rtkeluhanapp.model.RetrofitClient
import dev.ndridm.rtkeluhanapp.ressiskamling.ResponseSiskamling
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AdminSiskamling : AppCompatActivity() {

    lateinit var binding: ActivityAdminSiskamlingBinding

    private var tgl: String =""
    private var jumlah: String =""
    private var ronda_1: String =""
    private var ronda_2: String =""
    private var ronda_3: String =""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdminSiskamlingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener {
            startActivity(Intent(this@AdminSiskamling, MainActivityAdmin::class.java))
            finish()
        }

        binding.btnSiskam.setOnClickListener {
            tgl = binding.etTgls.text.toString()
            jumlah = binding.etJumlah.text.toString()
            ronda_1 = binding.etRonda1.text.toString()
            ronda_2 = binding.etRonda2.text.toString()
            ronda_3 = binding.etRonda3.text.toString()

            when {
                tgl ==""->{binding.etTgls.error = "Tidak boleh kosong"}
                jumlah ==""->{binding.etJumlah.error = "Tidak boleh kosong"}
                ronda_1 ==""->{binding.etRonda1.error = "Tidak boleh kosong"}
                ronda_2 ==""->{binding.etRonda2.error = "Tidak boleh kosong"}
                ronda_3 ==""->{binding.etRonda3.error = "Tidak boleh kosong"}

                else ->{
                    binding.progressBar.visibility = View.VISIBLE
                    createDataSiskamling()
                }
            }
        }
    }

    fun createDataSiskamling(){
        val model = RetrofitClient().getSiskamling()
        model.addSiskamling(
            binding.etTgls.text.toString(),
            binding.etJumlah.text.toString(),
            binding.etRonda1.text.toString(),
            binding.etRonda2.text.toString(),
            binding.etRonda3.text.toString()
        ).enqueue(object : Callback<ResponseSiskamling>{
            override fun onResponse(
                call: Call<ResponseSiskamling>,
                response: Response<ResponseSiskamling>
            ) {
                if (response.isSuccessful){
                    Toast.makeText(this@AdminSiskamling, "Jadwal siskamling disimpan", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this@AdminSiskamling, MainActivityAdmin::class.java))
                    binding.progressBar.visibility = View.VISIBLE
                    finish()
                }else {
                    binding!!.progressBar.visibility = View.GONE
                    Toast.makeText(this@AdminSiskamling, "Gagal Disimpan", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ResponseSiskamling>, t: Throwable) {
                Log.e("Pesan Error", t.message.toString())
            }

        })
    }
}