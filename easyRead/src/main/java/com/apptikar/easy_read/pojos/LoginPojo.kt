package com.apptikar.easy_read.pojos


import com.google.gson.annotations.SerializedName

data class LoginPojo(
    val status: Int,
    val message: String,
    val `data`: DataX
)