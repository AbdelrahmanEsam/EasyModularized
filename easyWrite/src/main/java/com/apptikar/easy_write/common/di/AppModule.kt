package com.apptikar.easy_write.common.di

import android.content.Context
import com.apptikar.common.utils.ConnectivityObserver
import com.apptikar.common.utils.NetworkConnectivityObserver
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule
{



    @Singleton
    @Provides
    fun connectivityObserver(@ApplicationContext context: Context) : ConnectivityObserver = NetworkConnectivityObserver(context = context)


}