package com.apptikar.easy_read.di

import android.content.Context
import com.apptikar.common.utils.ConnectivityObserver
import com.apptikar.common.utils.NetworkConnectivityObserver
import com.apptikar.login.data.remote.RetrofitApiLogin
import com.apptikar.scan.remote.RetrofitApiScan
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule
{
    private val retrofitObject  = Retrofit.Builder().baseUrl("http://sahel.ahmeds.club/").addConverterFactory(
         GsonConverterFactory.create()).build()



    @Singleton
    @Provides
    fun retrofitBuilder(): RetrofitApiLogin = retrofitObject.create(RetrofitApiLogin::class.java)

    @Singleton
    @Provides
    fun retrofitReadBuilder(): RetrofitApiScan = retrofitObject.create(RetrofitApiScan::class.java)

    @Singleton
    @Provides
    fun connectivityObserver(@ApplicationContext context: Context) : ConnectivityObserver = NetworkConnectivityObserver(context = context)




}