package com.apptikar.easy_read.pojos


import com.google.gson.annotations.SerializedName

data class CheckPojo(
    var status: Int,
    var message: String,
    var code : String
){

}