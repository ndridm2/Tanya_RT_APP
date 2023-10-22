package dev.ndridm.rtkeluhanapp.model

import dev.ndridm.rtkeluhanapp.reskeluhan.ResponseKeluhan
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface KeluhanClient {

    @FormUrlEncoded
    @POST("keluhan_post.php")
    fun addKeluhan(
        @Field("nama")              nama : String,
        @Field("rumah")             rumah : String,
        @Field("rt")                rt : String,
        @Field("jenis_keluhan")     jenis_keluhan : String,
        @Field("ket")               ket : String
    ): Call<ResponseKeluhan>

    @GET("keluhan.php")
    fun getkeluhan(): Call<ArrayList<ResponseKeluhan>>


}