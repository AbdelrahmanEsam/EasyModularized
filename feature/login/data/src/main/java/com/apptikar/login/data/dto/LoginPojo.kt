package com.apptikar.login.data.dto

import com.apptikar.login.domain.models.DataX

data class LoginPojo(
    val status: Int,
    val message: String,
    val `data`: DataX
)