package com.apptikar.login.di

import com.apptikar.login.admin.domain.repository.LoginRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent


@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {



//     @Binds
//     abstract fun loginRepoProvider(repo: LoginRepoImp): LoginRepo



}