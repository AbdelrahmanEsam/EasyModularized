package com.apptikar.easy_write.common.di

import android.content.Context
import com.apptikar.easy_write.common.utils.ConnectivityObserver
import com.apptikar.easy_write.common.utils.NetworkConnectivityObserver
import com.apptikar.easy_write.data.remote.RetrofitApi
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

    @Singleton
    @Provides
    fun retrofitBuilder(): RetrofitApi = Retrofit.Builder().baseUrl("http://sahel.ahmeds.club/").addConverterFactory(
        GsonConverterFactory.create()).build()
        .create(RetrofitApi::class.java)

    @Singleton
    @Provides
    fun connectivityObserver(@ApplicationContext context: Context) : ConnectivityObserver = NetworkConnectivityObserver(context = context)








}