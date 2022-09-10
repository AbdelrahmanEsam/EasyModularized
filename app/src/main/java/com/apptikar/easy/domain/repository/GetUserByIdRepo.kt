package com.apptikar.easy.domain.repository

import com.apptikar.easy.data.dto.LoginPojo
import com.apptikar.easy.data.dto.UserByIdPojo
import retrofit2.Response

interface GetUserByIdRepo {

    suspend fun getUserById(userId:String) : Response<UserByIdPojo>
}