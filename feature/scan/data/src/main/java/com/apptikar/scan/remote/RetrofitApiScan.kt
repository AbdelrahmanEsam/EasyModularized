package com.apptikar.scan.remote
import com.apptikar.scan.data.dto.CheckDto
import com.apptikar.scan.data.dto.MakeInvoiceDto
import retrofit2.Response
import retrofit2.http.*

interface RetrofitApiScan {

//    @FormUrlEncoded
//    @POST("api/Auth_general/login")
//    suspend fun login(@Field("code") code : String , @Field("password") password :String) : Response<LoginDto>



//    @GET("api/General/getUserById")
//    suspend fun getUserById(@Query("user_id")id:Int) : Response<UserByIdPojo>

    @FormUrlEncoded
    @POST("api/General/makeInvoice")
    suspend fun makeInvoice(@Header("Authorization") token:String,@Field("code") code : String, @Field("cost") cost :String) : Response<MakeInvoiceDto>

    @GET("api/General/getUserById")
    suspend fun checkUser(@Header("Authorization") token:String,@Query("code") code : String) : Response<CheckDto>

}