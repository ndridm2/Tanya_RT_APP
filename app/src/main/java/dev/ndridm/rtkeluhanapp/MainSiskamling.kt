package dev.ndridm.rtkeluhanapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import dev.ndridm.rtkeluhanapp.databinding.ActivityMainSiskamlingBinding
import dev.ndridm.rtkeluhanapp.model.RetrofitClient
import dev.ndridm.rtkeluhanapp.ressiskamling.ResponseSiskamling
import dev.ndridm.rtkeluhanapp.ressiskamling.SisAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainSiskamling : AppCompatActivity() {

    private lateinit var list: ArrayList<ResponseSiskamling>
    lateinit var binding: ActivityMainSiskamlingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainSiskamlingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        list = ArrayList()

        binding.btnBack.setOnClickListener {
            startActivity(Intent(this@MainSiskamling, MainActivityUser::class.java))
            finish()
        }

        binding.rvSiskamling.setHasFixedSize(true)
        binding.rvSiskamling.layoutManager = LinearLayoutManager (this)
        getSiskamlings()
    }

    private fun getSiskamlings(){
        val model = RetrofitClient().getSiskamling()
        model.getSiskamling().enqueue(object : Callback<ArrayList<ResponseSiskamling>>{
            override fun onResponse(
                call: Call<ArrayList<ResponseSiskamling>>,
                response: Response<ArrayList<ResponseSiskamling>>
            ) {
                val responseCode: String = response.code().toString()
                Log.i(responseCode, "onResponse")

                response.body()?.let { list.addAll(it) }
                var adapter = SisAdapter(list)
                binding.rvSiskamling.adapter = adapter
            }

            override fun onFailure(call: Call<ArrayList<ResponseSiskamling>>, t: Throwable) {
                Log.e("Pesan error", "${t.message}")
            }

        })
    }
}