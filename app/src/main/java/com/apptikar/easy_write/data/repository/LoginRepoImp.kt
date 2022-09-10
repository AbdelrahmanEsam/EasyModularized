package com.apptikar.easy_write.data.repository

import com.apptikar.easy_write.domain.repository.LoginRepo
import com.apptikar.easy_write.data.remote.RetrofitApi
import com.apptikar.easy_write.data.dto.LoginPojo
import retrofit2.Response
import javax.inject.Inject

class LoginRepoImp  @Inject constructor(private val retrofitApi: RetrofitApi) : LoginRepo {
    override suspend fun login(code: String, password: String): Response<LoginPojo> {
       return retrofitApi.login(code,password)
    }
}