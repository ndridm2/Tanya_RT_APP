package dev.ndridm.rtkeluhanapp

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import dev.ndridm.rtkeluhanapp.databinding.ActivityAdminSettingsBinding

class AdminSettings : AppCompatActivity() {

    private lateinit var binding: ActivityAdminSettingsBinding
    lateinit var profile : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdminSettingsBinding.inflate(layoutInflater)
        profile = getSharedPreferences("login_session", MODE_PRIVATE)
        setContentView(binding.root)

        binding.bottomNav.setOnItemSelectedListener{

            when(it.itemId){
                R.id.home -> startActivity(Intent(this@AdminSettings, MainActivityAdmin::class.java))
                R.id.profile -> startActivity(Intent(this@AdminSettings, AdminProfile::class.java))
                R.id.settings -> startActivity(Intent(this@AdminSettings, AdminSettings::class.java))

                else ->{

                }
            }
            true
        }

        binding.btnLogout.setOnClickListener {
            profile.edit().clear().commit()
            startActivity(Intent(this@AdminSettings, LoginActivity::class.java))
            finish()
            Toast.makeText(this@AdminSettings, "Anda Telah Keluar!!", Toast.LENGTH_SHORT).show()
        }
    }
}