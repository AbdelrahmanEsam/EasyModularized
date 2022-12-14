package com.apptikar.writeontag.data.remote
import com.apptikar.writeontag.data.dto.UserByIdPojo
import retrofit2.Response
import retrofit2.http.*

interface RetrofitApi {


    @GET("apiAdmin/User/single")
    suspend fun getUserById(@Header("Authorization") token:String? , @Query("user_id")id:String) : Response<UserByIdPojo>


}