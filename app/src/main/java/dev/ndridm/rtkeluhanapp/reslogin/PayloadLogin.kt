package dev.ndridm.rtkeluhanapp.reslogin

data class PayloadLogin(
    val id_user     :String,
    val email       :String,
    val username    :String,
    val role        :String,
    val nama        :String,
    val alamat      :String,
    val kelamin     :String,
    val agama       :String,
    val status      :String
)
