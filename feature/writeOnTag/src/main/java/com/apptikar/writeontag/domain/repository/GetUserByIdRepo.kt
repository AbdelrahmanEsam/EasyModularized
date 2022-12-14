package com.apptikar.writeontag.domain.repository

import com.apptikar.writeontag.data.dto.UserByIdPojo
import retrofit2.Response

interface GetUserByIdRepo {

    suspend fun getUserById(userId:String , token :String?) : Response<com.apptikar.writeontag.data.dto.UserByIdPojo>
}