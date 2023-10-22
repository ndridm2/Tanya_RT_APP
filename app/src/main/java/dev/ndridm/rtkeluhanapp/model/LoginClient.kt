package dev.ndridm.rtkeluhanapp.model

import dev.ndridm.rtkeluhanapp.reslogin.ResponseLogin
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface LoginClient {

    @FormUrlEncoded
    @POST("login.php")
    fun login(
        @Field("post_username") username : String,
        @Field("post_password") password : String
    ): Call<ResponseLogin>

}