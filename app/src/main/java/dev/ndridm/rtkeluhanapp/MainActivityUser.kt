package dev.ndridm.rtkeluhanapp

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import dev.ndridm.rtkeluhanapp.databinding.ActivityMainUserBinding

class MainActivityUser : AppCompatActivity() {

    lateinit var binding: ActivityMainUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
        menuBtn()

        binding.bottomNav.setOnItemSelectedListener {

            when(it.itemId){

                R.id.home -> startActivity(Intent(this@MainActivityUser, MainActivityUser::class.java))
                R.id.profile -> startActivity(Intent(this@MainActivityUser, MainProfile::class.java))
                R.id.settings -> startActivity(Intent(this@MainActivityUser, MainSettings::class.java))

                else ->{

                }
            }
            true
        }

    }

    private fun menuBtn(){

        binding.MenuKeluhan.setOnClickListener {
            startActivity(Intent(this@MainActivityUser,
                KeluhanActivity::class.java))
        }
        binding.MenuSiskamling.setOnClickListener {
            startActivity(Intent(this@MainActivityUser,
                MainSiskamling::class.java))
        }
        binding.MenuKerbak.setOnClickListener {
            startActivity(Intent(this@MainActivityUser,
                MainKerbak::class.java))
        }
        binding.panikButtom.setOnClickListener {
            Toast.makeText(this@MainActivityUser, "Bantuan akan segera datang!!", Toast.LENGTH_LONG).show()
        }
    }

}