package com.apptikar.login.data.mappers
import com.apptikar.login.data.dto.LoginPojo
import com.apptikar.login.admin.domain.models.LoginModel

fun LoginPojo.toLoginModelMapper() : LoginModel
{
return LoginModel(status = status, message = message ,data = data)
}