package com.apptikar.easy_write.pojos


import com.apptikar.easy_write.data.dto.DataXX

data class MakeInvoicePojo(
    val status: Int,
    val message: String,
    val `data`: DataXX
)