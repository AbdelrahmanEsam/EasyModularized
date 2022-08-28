package com.apptikar.easy.peresentation.ui.login

import androidx.lifecycle.ViewModel
import com.apptikar.easy.pojos.LoginPojo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


class LoginViewModel  : ViewModel() {

   private val _code = MutableStateFlow<String?>(null)
    val code : StateFlow<String?> get() =  _code

    private val _password = MutableStateFlow<String?>(null)
    val password :StateFlow<String?> get() = _password


    private val _userInfo  =MutableStateFlow<LoginPojo?>(null)
    val userInfo: StateFlow<LoginPojo?> = _userInfo


  private  val loading = MutableStateFlow<Boolean?>(null)


    fun setCode(code:String){
        _code.value = code
    }

    fun  setPassword(password:String){
        _password.value = password
    }




//    fun login(){
//        loading.value = true
//        viewModelScope.launch(Dispatchers.IO)
//        {
//            val response =  loginRepo.login(code.value.toString(),password.value.toString())
//            loading.value = false
//            if (response.isSuccessful){
//                _userInfo.value = response.body()!!
//
//            }
//        }
//    }

    fun clearUserInfo(){
        _userInfo.value = null
    }


}