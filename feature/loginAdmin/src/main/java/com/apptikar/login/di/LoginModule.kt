package com.apptikar.login.di

import com.apptikar.login.data.remote.RetrofitApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object LoginModule
{

    @Singleton
    @Provides
    fun retrofitBuilder(): RetrofitApi = Retrofit.Builder().baseUrl("http://sahel.ahmeds.club/").addConverterFactory(
        GsonConverterFactory.create()).build()
        .create(RetrofitApi::class.java)









}