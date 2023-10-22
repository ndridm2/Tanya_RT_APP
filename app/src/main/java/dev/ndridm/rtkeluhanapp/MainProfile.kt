package dev.ndridm.rtkeluhanapp

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dev.ndridm.rtkeluhanapp.databinding.ActivityMainProfileBinding

class MainProfile : AppCompatActivity() {

    lateinit var binding: ActivityMainProfileBinding
    private lateinit var profile: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        profile = getSharedPreferences("login_session", MODE_PRIVATE)

        binding.tvNama.text = profile.getString("nama", null)
        binding.tvAlamat.text = profile.getString("alamat", null)
        binding.tvKelamin.text = profile.getString("kelamin", null)
        binding.tvAgama.text = profile.getString("agama", null)
        binding.tvStatus.text = profile.getString("status", null)

        binding.bottomNav.setOnItemSelectedListener {

            when(it.itemId){

                R.id.home -> startActivity(Intent(this@MainProfile, MainActivityUser::class.java))
                R.id.profile -> startActivity(Intent(this@MainProfile, MainProfile::class.java))
                R.id.settings -> startActivity(Intent(this@MainProfile, MainSettings::class.java))

                else ->{

                }
            }
            true
        }
    }
}