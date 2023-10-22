package dev.ndridm.rtkeluhanapp.model

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {

    private fun getRetrofitClient(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://gwpamijahan.000webhostapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    // Login
    fun getInstance(): LoginClient{
        return getRetrofitClient().create(LoginClient::class.java)
    }
    // get siskamling
    fun getSiskamling(): SiskamlingClient{
        return getRetrofitClient().create(SiskamlingClient::class.java)
    }
    // get kerjabakti
    fun getKerbak(): KerbakClient{
        return getRetrofitClient().create(KerbakClient::class.java)
    }
    //post keluhan
    fun postKeluhan(): KeluhanClient{
        return getRetrofitClient().create(KeluhanClient::class.java)
    }
    //register
    fun postRegister(): RegisterClient{
        return getRetrofitClient().create(RegisterClient::class.java)
    }

}