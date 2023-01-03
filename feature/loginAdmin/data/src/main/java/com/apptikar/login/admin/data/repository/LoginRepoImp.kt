package com.apptikar.login.admin.data.repository


import com.apptikar.login.admin.data.mappers.toLoginModelMapper
import com.apptikar.login.admin.data.remote.RetrofitApi
import com.apptikar.login.admin.domain.models.LoginModel
import com.apptikar.login.admin.domain.repository.LoginRepo
import retrofit2.Response
import javax.inject.Inject

class LoginRepoImp  @Inject constructor(private val retrofitApi: RetrofitApi) :
    LoginRepo {
    override suspend fun login(code: String, password: String): Response<LoginModel> {
       return Response.success(retrofitApi.login(code,password).body()?.toLoginModelMapper())
    }
}