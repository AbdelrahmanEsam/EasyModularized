package com.apptikar.easy.peresentation.ui.writeOnTag

import android.nfc.NdefMessage
import android.nfc.NdefRecord
import android.nfc.Tag
import android.nfc.tech.NdefFormatable
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apptikar.easy.common.utils.ConnectivityObserver
import com.apptikar.easy.domain.repository.GetUserByIdRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeout
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class InsertTheMountViewModel @Inject constructor( private val getUserByIdRepo: GetUserByIdRepo,private val connectivityObserver: ConnectivityObserver): ViewModel() {


    private val _userId = MutableStateFlow<String?>(null)
    val userId : StateFlow<String?> get() =  _userId

    private val _userCode = MutableStateFlow<String?>(null)


    private val _isLoading = MutableStateFlow(false)
    val isLoading : StateFlow<Boolean> get() =  _isLoading



    fun setUserId(userId : String?){
        _userId.update { userId }
    }


    fun getUserById()
    {
        _isLoading.update { true }

        try {
            viewModelScope.launch {
                withTimeout(2000L){
                    val user =  getUserByIdRepo.getUserById(userId.value!!)
                    _userCode.update {
                        user.code().toString()
                    }

                }
                _isLoading.update { false }

            }
        }catch (exception : Exception){
            _isLoading.update { false }
            Log.d("exception20",exception.message.toString())
        }

    }


     fun writeToTagByNFC(tag : Tag?): Boolean{
         var returnValue = false
         viewModelScope.launch(Dispatchers.IO) {
             tag?.let {
                 val ndefFormatable = NdefFormatable.get(tag)
                 if (ndefFormatable != null) {
                      try {
                         ndefFormatable.connect()
                         val records :Array<NdefRecord> = arrayOf(createRecord(_userCode.value.toString()))
                         val  message = NdefMessage(records)
                         ndefFormatable.format(message)
                        returnValue =  true
                     }catch (exception: Exception) {
                          Log.d("exception",exception.message.toString())
                     }finally {
                         try {
                             ndefFormatable.close()
                         } catch (exception: Exception ) {
                             Log.e("Formating message : " , exception.toString())
                         }
                     }
                 }
             }
         }

         return returnValue
    }

    private fun createRecord(stringContent: String): NdefRecord {
        val lang = "en"
        val langBytes = lang.toByteArray(charset("US-ASCII"))
        val textBytes = stringContent.toByteArray()
        val payload = ByteArray(1 + langBytes.size + textBytes.size)

        // the first byte of Ndef is the status of the lang
        payload[0] = langBytes.size.toByte()

        System.arraycopy(langBytes, 0, payload, 1, langBytes.size)
        System.arraycopy(textBytes, 0, payload, 1 + langBytes.size, textBytes.size)

        return NdefRecord(NdefRecord.TNF_WELL_KNOWN, NdefRecord.RTD_TEXT, byteArrayOf(), payload)
    }






}