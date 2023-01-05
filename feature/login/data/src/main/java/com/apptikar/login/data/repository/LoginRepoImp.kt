package com.apptikar.login.data.repository


import com.apptikar.login.data.mappers.toLoginModelMapper
import com.apptikar.login.data.remote.RetrofitApiLogin
import com.apptikar.login.domain.models.LoginModel
import com.apptikar.login.domain.repository.LoginRepo
import retrofit2.Response
import javax.inject.Inject

class LoginRepoImp  @Inject constructor(private val retrofitApi: RetrofitApiLogin) : LoginRepo {
    override suspend fun login(code: String, password: String): Response<LoginModel> {
       return Response.success(retrofitApi.login(code,password).body()?.toLoginModelMapper())
    }
}