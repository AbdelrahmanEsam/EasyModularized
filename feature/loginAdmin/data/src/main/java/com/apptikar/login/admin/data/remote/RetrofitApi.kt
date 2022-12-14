package com.apptikar.login.admin.data.remote

import com.apptikar.login.admin.data.dto.LoginPojo
import retrofit2.Response
import retrofit2.http.*

interface RetrofitApi {

    @FormUrlEncoded
    @POST("apiAdmin/Auth_general/login")
    suspend fun login(@Field("name") name : String , @Field("password") password :String) : Response<LoginPojo>




}