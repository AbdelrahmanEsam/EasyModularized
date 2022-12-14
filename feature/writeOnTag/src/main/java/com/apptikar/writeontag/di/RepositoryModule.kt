package com.apptikar.writeontag.di


import com.apptikar.writeontag.data.repository.GetUserByIdRepoImp
import com.apptikar.writeontag.domain.repository.GetUserByIdRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent


@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {





     @Binds
     abstract fun getUserById(repo: GetUserByIdRepoImp): GetUserByIdRepo


}