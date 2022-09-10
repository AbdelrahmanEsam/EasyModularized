package com.apptikar.easy_write.peresentation.ui.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apptikar.easy_write.domain.repository.LoginRepo
import com.apptikar.easy_write.data.dto.LoginPojo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(private val loginRepo: LoginRepo)  : ViewModel() {

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




    fun login(){
        loading.value = true
        viewModelScope.launch(Dispatchers.IO)
        {
            Log.e("login" ,code.value.toString()+"\n"+password.value.toString() )
            val response =  loginRepo.login(code.value.toString(),password.value.toString())
            loading.value = false
            if (response.isSuccessful){
                _userInfo.value = response.body()!!
            }
        }
    }

    fun clearUserInfo(){
        _userInfo.value = null
    }


}