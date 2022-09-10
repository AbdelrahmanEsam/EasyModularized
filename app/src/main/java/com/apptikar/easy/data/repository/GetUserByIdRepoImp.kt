package com.apptikar.easy.data.repository

import com.apptikar.easy.data.dto.UserByIdPojo
import com.apptikar.easy.data.remote.RetrofitApi
import com.apptikar.easy.domain.repository.GetUserByIdRepo
import retrofit2.Response
import javax.inject.Inject

class GetUserByIdRepoImp @Inject constructor(private val retrofitApi: RetrofitApi)  : GetUserByIdRepo {

    override suspend fun getUserById(userId: String , token : String?): Response<UserByIdPojo> {

        return retrofitApi.getUserById( token ,userId)
    }
}