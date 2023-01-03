package com.apptikar.scan.data.repositories



import com.apptikar.scan.data.mappers.toCheckModel
import com.apptikar.scan.data.mappers.toMakeInvoiceModel
import com.apptikar.scan.domain.model.CheckModel
import com.apptikar.scan.domain.model.MakeInvoiceModel
import com.apptikar.scan.domain.repositories.MakeInvoiceRepo
import com.apptikar.scan.remote.RetrofitApiScan
import retrofit2.Response
import javax.inject.Inject

class MakeInvoiceImp @Inject constructor(private val retrofitApi: RetrofitApiScan) : MakeInvoiceRepo {
    override suspend fun makeInvoice(token: String, code: String, cost: String): Response<MakeInvoiceModel> {
          return Response.success(retrofitApi.makeInvoice("Bearer $token",code,cost).body()?.toMakeInvoiceModel())
    }

    override suspend fun checkUser(token :String,code: String): Response<CheckModel> {
        return Response.success(retrofitApi.checkUser("Bearer $token",code).body()?.toCheckModel())
    }
}