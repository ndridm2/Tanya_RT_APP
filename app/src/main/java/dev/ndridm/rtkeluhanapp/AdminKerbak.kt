package dev.ndridm.rtkeluhanapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import dev.ndridm.rtkeluhanapp.databinding.ActivityAdminKerbakBinding
import dev.ndridm.rtkeluhanapp.model.RetrofitClient
import dev.ndridm.rtkeluhanapp.reskerbak.ResponseKerbak
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AdminKerbak : AppCompatActivity() {

    lateinit var binding: ActivityAdminKerbakBinding

    private var tgl: String =""
    private var kegiatan: String =""
    private var lokasi: String =""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdminKerbakBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener {
            startActivity(Intent(this@AdminKerbak, MainActivityAdmin::class.java))
            finish()
        }

        binding.btnSaveKerbak.setOnClickListener {
            tgl = binding.etTgl.text.toString()
            kegiatan = binding.etKegiatan.text.toString()
            lokasi = binding.etLokasi.text.toString()

            when {
                tgl ==""->{binding.etTgl.error = "Tidak boleh kosong"}
                kegiatan ==""->{binding.etKegiatan.error = "Tidak boleh kosong"}
                lokasi ==""->{binding.etLokasi.error = "Tidak boleh kosong"}

                else ->{
                    binding.progressBar.visibility = View.VISIBLE
                    createDataKerbak()
                }
            }
        }
    }

    fun createDataKerbak(){
        val model = RetrofitClient().getKerbak()
        model.addKerbak(
            binding.etTgl.text.toString(),
            binding.etKegiatan.text.toString(),
            binding.etLokasi.text.toString()
        ).enqueue(object : Callback<ResponseKerbak>{
            override fun onResponse(
                call: Call<ResponseKerbak>,
                response: Response<ResponseKerbak>
            ) {
                if (response.isSuccessful){
                    Toast.makeText(this@AdminKerbak, "Jadwal Kerja bakti disimpan", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this@AdminKerbak, MainActivityAdmin::class.java))
                    binding.progressBar.visibility = View.VISIBLE
                    finish()
                }else {
                    binding!!.progressBar.visibility = View.GONE
                    Toast.makeText(this@AdminKerbak, "Gagal Disimpan", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ResponseKerbak>, t: Throwable) {
                Log.e("Pesan Error", t.message.toString())
            }

        })
    }
}