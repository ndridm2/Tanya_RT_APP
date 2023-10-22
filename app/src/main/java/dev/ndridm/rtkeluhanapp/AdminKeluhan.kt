package dev.ndridm.rtkeluhanapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import dev.ndridm.rtkeluhanapp.databinding.ActivityAdminKeluhanBinding
import dev.ndridm.rtkeluhanapp.model.RetrofitClient
import dev.ndridm.rtkeluhanapp.reskeluhan.KeluhanAdapter
import dev.ndridm.rtkeluhanapp.reskeluhan.ResponseKeluhan
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AdminKeluhan : AppCompatActivity() {

    private lateinit var list: ArrayList<ResponseKeluhan>
    lateinit var binding: ActivityAdminKeluhanBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdminKeluhanBinding.inflate(layoutInflater)
        setContentView(binding.root)
        list = ArrayList()

        binding.btnBack.setOnClickListener {
            startActivity(Intent(this@AdminKeluhan, MainActivityAdmin::class.java))
            finish()
        }

        binding.rvSiskamling.setHasFixedSize(true)
        binding.rvSiskamling.layoutManager = LinearLayoutManager(this)

        getDataKel()
    }

    private fun getDataKel(){
        val modal = RetrofitClient().postKeluhan()
        modal.getkeluhan().enqueue(object : Callback<ArrayList<ResponseKeluhan>>{
            override fun onResponse(
                call: Call<ArrayList<ResponseKeluhan>>,
                response: Response<ArrayList<ResponseKeluhan>>
            ) {
                val responseCode: String = response.code().toString()
                Log.i(responseCode, "onResponse")

                response.body()?.let { list.addAll(it) }
                var adapter = KeluhanAdapter(list)
                binding.rvSiskamling.adapter = adapter
            }

            override fun onFailure(call: Call<ArrayList<ResponseKeluhan>>, t: Throwable) {
                Log.e("Pesan error", "${t.message}")
            }

        })
    }
}