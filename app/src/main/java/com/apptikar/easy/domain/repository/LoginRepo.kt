package com.apptikar.easy.domain.repository

import com.apptikar.easy.data.dto.LoginPojo
import retrofit2.Response

interface LoginRepo {

    suspend fun login(code:String,password:String) : Response<LoginPojo>
}