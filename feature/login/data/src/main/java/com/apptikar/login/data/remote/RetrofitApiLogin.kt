package com.apptikar.login.data.remote

import com.apptikar.login.data.dto.LoginPojo
import retrofit2.Response
import retrofit2.http.*

interface RetrofitApiLogin {


    @FormUrlEncoded
    @POST("api/Auth_general/login")
    suspend fun login(@Field("code") code : String , @Field("password") password :String) : Response<LoginPojo>


}