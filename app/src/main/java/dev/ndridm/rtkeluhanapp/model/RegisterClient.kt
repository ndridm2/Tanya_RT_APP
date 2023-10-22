package dev.ndridm.rtkeluhanapp.model

import dev.ndridm.rtkeluhanapp.resregis.ResponseRegister
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface RegisterClient {

    @FormUrlEncoded
    @POST("register.php")
    fun register(
        @Field("email") email: String,
        @Field("username") username: String,
        @Field("password") password: String,
        @Field("role") role: String,
        @Field("nama") nama: String,
        @Field("alamat") alamat: String,
        @Field("kelamin") kelamin: String,
        @Field("agama") agama: String,
        @Field("status") status: String
    ): Call<ResponseRegister>
}