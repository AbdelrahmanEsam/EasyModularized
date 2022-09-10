package com.apptikar.easy_write.domain.repository

import com.apptikar.easy_write.data.dto.UserByIdPojo
import retrofit2.Response

interface GetUserByIdRepo {

    suspend fun getUserById(userId:String , token :String?) : Response<UserByIdPojo>
}