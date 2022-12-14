package com.apptikar.login.di


import com.apptikar.domain.repository.LoginRepo
import com.apptikar.login.data.repository.LoginRepoImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent


@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {



     @Binds
     abstract fun loginRepoProvider(repo: LoginRepoImp): LoginRepo



}