package com.apptikar.scan.domain.repositories
import com.apptikar.scan.domain.model.CheckModel
import com.apptikar.scan.domain.model.MakeInvoiceModel
import retrofit2.Response

interface MakeInvoiceRepo {
   suspend  fun makeInvoice(token:String,code:String,cost:String) : Response<MakeInvoiceModel>
   suspend fun checkUser(token:String ,code:String) : Response<CheckModel>
}