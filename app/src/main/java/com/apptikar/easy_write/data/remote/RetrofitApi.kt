package com.apptikar.easy_write.data.remote
import com.apptikar.easy_write.data.dto.LoginPojo
import com.apptikar.easy_write.data.dto.UserByIdPojo
import retrofit2.Response
import retrofit2.http.*

interface RetrofitApi {

    @FormUrlEncoded
    @POST("apiAdmin/Auth_general/login")
    suspend fun login(@Field("name") name : String , @Field("password") password :String) : Response<LoginPojo>


    @GET("apiAdmin/User/single")
    suspend fun getUserById(@Header("Authorization") token:String? , @Query("user_id")id:String) : Response<UserByIdPojo>


}