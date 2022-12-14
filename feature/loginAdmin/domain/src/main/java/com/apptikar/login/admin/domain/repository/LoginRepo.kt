package com.apptikar.login.admin.domain.repository


import com.apptikar.login.admin.domain.models.LoginModel
import retrofit2.Response

interface LoginRepo {

    suspend fun login(code:String,password:String) : Response<LoginModel>
}