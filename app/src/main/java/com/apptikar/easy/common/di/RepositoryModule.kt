package com.apptikar.easy.common.di


import com.apptikar.easy.data.repository.GetUserByIdRepoImp
import com.apptikar.easy.data.repository.LoginRepoImp
import com.apptikar.easy.domain.repository.GetUserByIdRepo
import com.apptikar.easy.domain.repository.LoginRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent


@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {



     @Binds
     abstract fun loginRepoProvider(repo: LoginRepoImp): LoginRepo

     @Binds
     abstract fun getUserById(repo: GetUserByIdRepoImp): GetUserByIdRepo


}