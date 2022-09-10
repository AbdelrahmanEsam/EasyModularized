package com.apptikar.easy.data.remote
import com.apptikar.easy.data.dto.LoginPojo
import com.apptikar.easy.data.dto.UserByIdPojo
import retrofit2.Response
import retrofit2.http.*

interface RetrofitApi {

    @FormUrlEncoded
    @POST("api/Auth_general/login")
    suspend fun login(@Field("code") code : String , @Field("password") password :String) : Response<LoginPojo>


    @GET("api/General/getUserById")
    suspend fun getUserById(@Query("user_id")id:String) : Response<UserByIdPojo>


}