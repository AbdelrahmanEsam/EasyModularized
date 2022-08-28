package com.apptikar.easy.pojos


import com.apptikar.easy.data.dto.Data

data class UserByIdPojo(
    val status: Int,
    val message: String,
    val `data`: Data
)