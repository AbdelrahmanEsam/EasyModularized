package com.apptikar.easy_write.common.di


import com.apptikar.easy_write.data.repository.GetUserByIdRepoImp
import com.apptikar.easy_write.data.repository.LoginRepoImp
import com.apptikar.easy_write.domain.repository.GetUserByIdRepo
import com.apptikar.easy_write.domain.repository.LoginRepo
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