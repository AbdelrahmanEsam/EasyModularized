package com.apptikar.easy_write.domain.repository

import com.apptikar.easy_write.data.dto.LoginPojo
import retrofit2.Response

interface LoginRepo {

    suspend fun login(code:String,password:String) : Response<LoginPojo>
}