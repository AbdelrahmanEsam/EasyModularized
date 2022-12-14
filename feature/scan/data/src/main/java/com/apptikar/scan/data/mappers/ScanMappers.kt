package com.apptikar.scan.data.mappers

import com.apptikar.scan.data.dto.CheckDto
import com.apptikar.scan.data.dto.MakeInvoiceDto
import com.apptikar.scan.domain.model.CheckModel
import com.apptikar.scan.domain.model.DataXX
import com.apptikar.scan.domain.model.MakeInvoiceModel

fun MakeInvoiceDto.toMakeInvoiceModel() : MakeInvoiceModel
{
 return MakeInvoiceModel(status = status , message = message ,data = DataXX(id = data.id , cost = data.cost,date = data.date ))
}


fun CheckDto.toCheckModel() : CheckModel
{
    return CheckModel(status = status , message = message , code = code)
}