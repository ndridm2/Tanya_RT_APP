package dev.ndridm.rtkeluhanapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import dev.ndridm.rtkeluhanapp.databinding.ActivityMainAdminBinding

class MainActivityAdmin : AppCompatActivity() {

    private lateinit var binding: ActivityMainAdminBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainAdminBinding.inflate(layoutInflater)
        setContentView(binding.root)
        menuAdmBtn()

        binding.bottomNav.setOnItemSelectedListener {

            when(it.itemId){

                R.id.home -> startActivity(Intent(this@MainActivityAdmin, MainActivityAdmin::class.java))
                R.id.profile -> startActivity(Intent(this@MainActivityAdmin, AdminProfile::class.java))
                R.id.settings -> startActivity(Intent(this@MainActivityAdmin, AdminSettings::class.java))

                else ->{

                }
            }
            true
        }
    }

    private fun menuAdmBtn(){

        binding.AdminMenuKeluhan.setOnClickListener {
            startActivity(Intent(this@MainActivityAdmin,
                AdminKeluhan::class.java))
        }
        binding.AdminMenuSiskamling.setOnClickListener {
            startActivity(Intent(this@MainActivityAdmin,
                AdminSiskamling::class.java))
        }
        binding.AdminMenuKerbak.setOnClickListener {
            startActivity(Intent(this@MainActivityAdmin,
                AdminKerbak::class.java))
        }
    }
}