package com.apptikar.easy.data.repository

import com.apptikar.easy.domain.repository.LoginRepo
import com.apptikar.easy.data.remote.RetrofitApi
import com.apptikar.easy.data.dto.LoginPojo
import retrofit2.Response
import javax.inject.Inject

class LoginRepoImp  @Inject constructor(private val retrofitApi: RetrofitApi) : LoginRepo {
    override suspend fun login(code: String, password: String): Response<LoginPojo> {
       return retrofitApi.login(code,password)
    }
}