package com.apptikar.domain.repository


import com.apptikar.domain.models.LoginModel
import retrofit2.Response

interface LoginRepo {

    suspend fun login(code:String,password:String) : Response<LoginModel>
}