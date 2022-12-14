package com.apptikar.easy_read.di



//import com.apptikar.easy_read.repositories.GetUserByIdImp
//import com.apptikar.easy_read.repositories.GetUserByIdRepo
import com.apptikar.scan.data.repositories.MakeInvoiceImp
import com.apptikar.scan.domain.repositories.MakeInvoiceRepo
//import com.apptikar.easy_read.repositories.LoginImp
import com.apptikar.login.admin.domain.repository.LoginRepo
import com.apptikar.login.data.repository.LoginRepoImp
//import com.apptikar.easy_read.repositories.LoginRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent


@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

//     @Binds
//     abstract fun getUserById(repo: GetUserByIdImp): GetUserByIdRepo

     @Binds
     abstract fun loginRepoProvider(repo: LoginRepoImp): LoginRepo

     @Binds
     abstract fun makeInvoiceProvider(repo: MakeInvoiceImp): MakeInvoiceRepo
}