package com.apptikar.easy_read.pojos


import com.google.gson.annotations.SerializedName

data class DataX(
    val id: Int,
    val name: String,
    val type: Int,
    val code: String,
    val balance: String,
    val token: String
)