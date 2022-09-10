package com.apptikar.easy_write.data.repository

import com.apptikar.easy_write.data.dto.UserByIdPojo
import com.apptikar.easy_write.data.remote.RetrofitApi
import com.apptikar.easy_write.domain.repository.GetUserByIdRepo
import retrofit2.Response
import javax.inject.Inject

class GetUserByIdRepoImp @Inject constructor(private val retrofitApi: RetrofitApi)  : GetUserByIdRepo {

    override suspend fun getUserById(userId: String , token : String?): Response<UserByIdPojo> {

        return retrofitApi.getUserById( token ,userId)
    }
}