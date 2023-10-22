package dev.ndridm.rtkeluhanapp

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import dev.ndridm.rtkeluhanapp.databinding.ActivityLoginBinding
import dev.ndridm.rtkeluhanapp.model.RetrofitClient
import dev.ndridm.rtkeluhanapp.reslogin.ResponseLogin
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    lateinit var binding: ActivityLoginBinding

    private var user : String=""
    private var pass : String=""
    lateinit var profile : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRegist.setOnClickListener {
            startActivity(Intent(this@LoginActivity, Register::class.java))
            finish()
        }

        profile = getSharedPreferences("login_session", MODE_PRIVATE)
        if (profile.getString("username", null) !== null){
            if (profile.getString("role", null) == "1"){
                startActivity(Intent(this@LoginActivity, MainActivityAdmin::class.java))
                finish()
            }else {
                startActivity(Intent(this@LoginActivity, MainActivityUser::class.java))
            }
        }

        binding.btnLogin.setOnClickListener {
            user = binding.tvUsername.text.toString()
            pass = binding.tvPassword.text.toString()

            when {
                user == ""->{ binding.tvUsername.error = "Username tidak boleh kosong!"}
                pass == ""->{binding.tvPassword.error = "Password tidak boleh kosong!"}
                else -> {
                    binding.progressBar.visibility = View.VISIBLE
                    getData()
                }
            }
        }
    }

    private fun getData(){
        val model = RetrofitClient().getInstance()
        model.login(user, pass).enqueue(object : Callback<ResponseLogin>{
            override fun onResponse(call: Call<ResponseLogin>, response: Response<ResponseLogin>) {
                if (response.isSuccessful) {
                    if (response.body()?.response == true) {

                        getSharedPreferences("login_session", MODE_PRIVATE)
                            .edit()
                            .putString("id_user", response.body()?.payload?.id_user)
                            .putString("email", response.body()?.payload?.email)
                            .putString("username", response.body()?.payload?.username)
                            .putString("role", response.body()?.payload?.role)
                            .putString("nama", response.body()?.payload?.nama)
                            .putString("alamat", response.body()?.payload?.alamat)
                            .putString("kelamin", response.body()?.payload?.kelamin)
                            .putString("agama", response.body()?.payload?.agama)
                            .putString("status", response.body()?.payload?.status)
                            .apply()

                        if (response.body()?.payload?.role == "1") {
                            binding.progressBar.visibility = View.GONE
                            startActivity(Intent(this@LoginActivity, MainActivityAdmin::class.java))
                            finish()
                        } else {
                            binding.progressBar.visibility = View.GONE
                            startActivity(Intent(this@LoginActivity, MainActivityUser::class.java))
                            finish()
                        }
                    } else {
                        binding.progressBar.visibility = View.GONE
                        Toast.makeText(
                            this@LoginActivity, "Login gagal! Periksa Kembali username & password",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }else {
                    Toast.makeText(this@LoginActivity, "Login gagal! terjadi kesalahan",
                        Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<ResponseLogin>, t: Throwable) {
                Log.e("Pesan error", "${t.message}")
            }

        })
    }
}