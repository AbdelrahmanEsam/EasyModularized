package com.apptikar.writeontag.data.repository

import com.apptikar.writeontag.data.dto.UserByIdPojo
import com.apptikar.writeontag.data.remote.RetrofitApi
import com.apptikar.writeontag.domain.repository.GetUserByIdRepo
import retrofit2.Response
import javax.inject.Inject

class GetUserByIdRepoImp @Inject constructor(private val retrofitApi: RetrofitApi)  :
    GetUserByIdRepo {

    override suspend fun getUserById(userId: String , token : String?): Response<UserByIdPojo> {

        return retrofitApi.getUserById( token ,userId)
    }
}