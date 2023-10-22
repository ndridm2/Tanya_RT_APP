package dev.ndridm.rtkeluhanapp

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dev.ndridm.rtkeluhanapp.databinding.ActivityAdminProfileBinding

class AdminProfile : AppCompatActivity() {

    private lateinit var binding: ActivityAdminProfileBinding
    private lateinit var profile: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdminProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        profile = getSharedPreferences("login_session", MODE_PRIVATE)

        binding.tvNama.text = profile.getString("nama", null)
        binding.tvAlamat.text = profile.getString("alamat", null)
        binding.tvKelamin.text = profile.getString("kelamin", null)
        binding.tvAgama.text = profile.getString("agama", null)
        binding.tvStatus.text = profile.getString("nama", null)

        binding.bottomNav.setOnItemSelectedListener{

            when(it.itemId){
                R.id.home -> startActivity(Intent(this@AdminProfile, MainActivityAdmin::class.java))
                R.id.profile -> startActivity(Intent(this@AdminProfile, AdminProfile::class.java))
                R.id.settings -> startActivity(Intent(this@AdminProfile, AdminSettings::class.java))
                else ->{
                }
            }
            true
        }
    }
}