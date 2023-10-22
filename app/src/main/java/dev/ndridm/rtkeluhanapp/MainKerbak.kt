package dev.ndridm.rtkeluhanapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import dev.ndridm.rtkeluhanapp.databinding.ActivityMainKerbakBinding
import dev.ndridm.rtkeluhanapp.model.RetrofitClient
import dev.ndridm.rtkeluhanapp.reskerbak.KerAdapter
import dev.ndridm.rtkeluhanapp.reskerbak.ResponseKerbak
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainKerbak : AppCompatActivity() {

  private lateinit var list: ArrayList<ResponseKerbak>
    lateinit var binding: ActivityMainKerbakBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainKerbakBinding.inflate(layoutInflater)
        setContentView(binding.root)
        list = ArrayList()

        binding.btnBack.setOnClickListener {
            startActivity(Intent(this@MainKerbak, MainActivityUser::class.java))
            finish()
        }

        binding.RvKerbak.setHasFixedSize(true)
        binding.RvKerbak.layoutManager = LinearLayoutManager(this)
        getData()

    }

    fun getData(){
        val model = RetrofitClient().getKerbak()
        model.getKerbak().enqueue(object : Callback<ArrayList<ResponseKerbak>>{
            override fun onResponse(
                call: Call<ArrayList<ResponseKerbak>>,
                response: Response<ArrayList<ResponseKerbak>>
            ) {
                val responseCode: String = response.code().toString()
                Log.i(responseCode, "onRespne")

                response.body()?.let { list.addAll(it) }
                var adapter = KerAdapter(list)
                binding.RvKerbak.adapter = adapter
            }

            override fun onFailure(call: Call<ArrayList<ResponseKerbak>>, t: Throwable) {
                Log.e("pesan error", "${t.message}")
            }

        })
    }

}