package dev.ndridm.rtkeluhanapp.model

import dev.ndridm.rtkeluhanapp.ressiskamling.ResponseSiskamling
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface SiskamlingClient {

    @FormUrlEncoded
    @POST("siskamling_post.php")
    fun addSiskamling(
        @Field("tgl") tgl: String,
        @Field("jumlah") jumlah: String,
        @Field("ronda_1") ronda_1: String,
        @Field("ronda_2") ronda_2: String,
        @Field("ronda_3") ronda_3: String,
    ): Call<ResponseSiskamling>

    @GET("siskamling.php")
    fun getSiskamling():Call<ArrayList<ResponseSiskamling>>
}