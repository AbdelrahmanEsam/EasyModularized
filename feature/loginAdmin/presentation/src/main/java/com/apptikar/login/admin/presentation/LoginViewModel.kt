package com.apptikar.login.admin.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apptikar.login.admin.domain.models.LoginModel
import com.apptikar.login.admin.domain.repository.LoginRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(private val loginRepo: LoginRepo)  : ViewModel() {

   private val _code = MutableStateFlow<String?>(null)
    val code : StateFlow<String?> get() =  _code

    private val _password = MutableStateFlow<String?>(null)
    val password :StateFlow<String?> get() = _password


    private val _userInfo  =MutableStateFlow<LoginModel?>(null)
    val userInfo: StateFlow<LoginModel?> = _userInfo


    private  val _loading = MutableStateFlow<Boolean?>(null)
    val loading : StateFlow<Boolean?> get() = _loading

    fun setCode(code:String){
        _code.value = code
    }

    fun  setPassword(password:String){
        _password.value = password
    }




    fun login(){
        _loading.update { true  }
        viewModelScope.launch(Dispatchers.IO)
        {
            Log.e("login" ,code.value.toString()+"\n"+password.value.toString() )
            val response =  loginRepo.login(code.value.toString(),password.value.toString())

            if (response.isSuccessful)
            {
                _loading.update { false }
                _userInfo.update { LoginModel() }
            Log.e("login" ,"status is "+ userInfo.value.toString())
            }


        }
    }

    fun clearUserInfo(){
        _userInfo.update { null }
    }


}