package dev.ndridm.rtkeluhanapp.model

import dev.ndridm.rtkeluhanapp.reskerbak.ResponseKerbak
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface KerbakClient {

    @FormUrlEncoded
    @POST("kerjabakti_post.php")
    fun addKerbak(
        @Field("tgl") tgl :String,
        @Field("kegiatan") kegiatan :String,
        @Field("lokasi") lokasi :String
    ): Call<ResponseKerbak>

    @GET("kerjabakti.php")
    fun getKerbak():Call<ArrayList<ResponseKerbak>>
}