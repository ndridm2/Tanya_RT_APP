package dev.ndridm.rtkeluhanapp.resregis

data class ResponseRegister(
    val id_user     :String,
    val email       :String,
    val username    :String,
    val password    :String,
    val role        :String,
    val nama        :String,
    val alamat      :String,
    val kelamin     :String,
    val agama       :String,
    val status      :String
)
