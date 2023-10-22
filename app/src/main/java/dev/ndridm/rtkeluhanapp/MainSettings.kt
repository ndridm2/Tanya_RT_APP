package dev.ndridm.rtkeluhanapp

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import dev.ndridm.rtkeluhanapp.databinding.ActivityMainSettingsBinding

class MainSettings : AppCompatActivity() {

    private lateinit var binding: ActivityMainSettingsBinding
    lateinit var profile : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainSettingsBinding.inflate(layoutInflater)
        profile = getSharedPreferences("login_session", MODE_PRIVATE)
        setContentView(binding.root)

        binding.bottomNav.setOnItemSelectedListener{

            when(it.itemId){
                R.id.home -> startActivity(Intent(this@MainSettings, MainActivityUser::class.java))
                R.id.profile -> startActivity(Intent(this@MainSettings, MainProfile::class.java))
                R.id.settings -> startActivity(Intent(this@MainSettings, MainSettings::class.java))

                else ->{

                }
            }
            true
        }

        binding.btnLogout.setOnClickListener {
            profile.edit().clear().commit()
            startActivity(Intent(this@MainSettings, LoginActivity::class.java))
            finish()
            Toast.makeText(this@MainSettings, "Anda Telah Keluar!!", Toast.LENGTH_SHORT).show()
        }
    }
}