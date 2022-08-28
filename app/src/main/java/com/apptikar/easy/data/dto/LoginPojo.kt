package com.apptikar.easy.pojos


import com.apptikar.easy.data.dto.DataX

data class LoginPojo(
    val status: Int,
    val message: String,
    val `data`: DataX
)