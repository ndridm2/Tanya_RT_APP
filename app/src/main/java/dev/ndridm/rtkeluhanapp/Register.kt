package dev.ndridm.rtkeluhanapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import dev.ndridm.rtkeluhanapp.databinding.ActivityRegisterBinding
import dev.ndridm.rtkeluhanapp.model.RetrofitClient
import dev.ndridm.rtkeluhanapp.resregis.ResponseRegister
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Register : AppCompatActivity() {

    lateinit var binding: ActivityRegisterBinding

    private var email: String=""
    private var username: String=""
    private var password:   String=""
    private var role: String=""
    private var nama: String=""
    private var alamat: String=""
    private var kelamin: String=""
    private var agama:  String=""
    private var status: String=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        createItemDropdown()

        binding.tvAkunLogin.setOnClickListener {
            startActivity(Intent(this@Register, LoginActivity::class.java))
            finish()
        }

        binding.btnRegist.setOnClickListener {
            email = binding.tvEmail.text.toString()
            username = binding.tvUsername.text.toString()
            password = binding.tvPass.text.toString()
            role = binding.tvItemRole.text.toString()
            nama = binding.tvNama.text.toString()
            alamat = binding.tvAlamat.text.toString()
            kelamin = binding.tvItemKelamin.text.toString()
            agama   = binding.tvAgama.text.toString()
            status = binding.tvItemStatus.text.toString()

            when {
                email ==""->{binding.tvEmail.error = "Tidak boleh kosong"}
                username ==""->{binding.tvUsername.error = "Tidak boleh kosong"}
                password ==""->{binding.tvPass.error = "Tidak boleh kosong"}
                role ==""->{binding.tvItemRole.error = "Tidak boleh kosong"}
                nama ==""->{binding.tvNama.error = "Tidak boleh kosong"}
                alamat ==""->{binding.tvAlamat.error = "Tidak boleh kosong"}
                kelamin ==""->{binding.tvItemKelamin.error = "Tidak boleh kosong"}
                agama ==""->{binding.tvAgama.error = "Tidak boleh kosong"}
                status ==""->{binding.tvItemStatus.error = "Tidak boleh kosong"}

                else ->{
                    binding.progressBar.visibility = View.VISIBLE
                    craeteDataRegister()
                }
            }
        }

    }

    fun craeteDataRegister(){
        val model = RetrofitClient().postRegister()
        model.register(
            binding.tvEmail.text.toString(),
            binding.tvUsername.text.toString(),
            binding.tvPass.text.toString(),
            binding.tvItemRole.text.toString(),
            binding.tvNama.text.toString(),
            binding.tvAlamat.text.toString(),
            binding.tvItemKelamin.text.toString(),
            binding.tvAgama.text.toString(),
            binding.tvItemStatus.text.toString()
        ).enqueue(object : Callback<ResponseRegister>{
            override fun onResponse(
                call: Call<ResponseRegister>,
                response: Response<ResponseRegister>
            ) {
                if (response.isSuccessful){
                    Toast.makeText(this@Register, "RegistrasI berhasil", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this@Register, LoginActivity::class.java))
                    finish()
                }else {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(this@Register, "Registrasi gagal", Toast.LENGTH_SHORT).show()
                }
            }
            override fun onFailure(call: Call<ResponseRegister>, t: Throwable) {
                Log.e("Pesan Error", t.message.toString())
            }

        })
    }

    fun createItemDropdown(){
        val roles = listOf("2")
        val adapter = ArrayAdapter(this, R.layout.item_dropdown, roles)
        binding.tvItemRole.setAdapter(adapter)

        val kelamin = listOf("Laki-Laiki", "Prempuan")
        val adapterkelamin = ArrayAdapter(this, R.layout.item_dropdown, kelamin)
        binding.tvItemKelamin.setAdapter(adapterkelamin)

        val status = listOf("Kawin", "Belum Kawin")
        val adapterstatus = ArrayAdapter(this, R.layout.item_dropdown, status)
        binding.tvItemStatus.setAdapter(adapterstatus)
    }
}