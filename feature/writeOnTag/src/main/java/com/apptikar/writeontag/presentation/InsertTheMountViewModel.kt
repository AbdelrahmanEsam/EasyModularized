package com.apptikar.writeontag.presentation

import android.nfc.NdefMessage
import android.nfc.NdefRecord
import android.nfc.Tag
import android.nfc.tech.Ndef
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apptikar.writeontag.data.dto.UserByIdPojo
import com.apptikar.writeontag.domain.repository.GetUserByIdRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class InsertTheMountViewModel @Inject constructor(private val getUserByIdRepo: GetUserByIdRepo): ViewModel() {


    private val _userId = MutableStateFlow<String?>(null)
    val userId : StateFlow<String?> get() =  _userId

    private val _userCode = MutableStateFlow<String?>(null)

    private val _response = MutableStateFlow<UserByIdPojo?>(null)
    val response : StateFlow<UserByIdPojo?> get() =  _response

    private val _isLoading = MutableStateFlow(false)
    val isLoading : StateFlow<Boolean> get() =  _isLoading



    fun setUserId(userId : String?){
        _userId.update { userId }
    }


    fun setCode(code : String?){
        _userCode.update { code }
    }

    fun emptyResponse(){
        _response.update { null }
    }

    fun getUserById(token : String?)
    {
        _isLoading.update { true }

        try {
            viewModelScope.launch {
                   val response =  getUserByIdRepo.getUserById(userId.value!! , "Bearer $token")
                    Log.e("get User id body" , response.toString())
                    _response.update {
                        response.body()
                    }

                    _userCode.update {
                        _response.value?.data?.code
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
             tag?.let {
                    formatTag(it)
                 val ndefFormatable = Ndef.get(tag)
                 if (ndefFormatable != null) {
                      try {
                         ndefFormatable.connect()
                         val records :Array<NdefRecord> = arrayOf(createRecord(_userCode.value.toString()))
                          Log.e("from writer" , _userCode.value.toString())
                          val  message = NdefMessage(records)
                         ndefFormatable.writeNdefMessage(message)
                        returnValue =  true
                     }catch (exception: Exception) {
                          Log.d("exception",exception.toString())
                     }finally {
                         try {
                             ndefFormatable.close()
                         } catch (exception: Exception ) {
                             Log.e("Formating message : " , exception.toString())
                         }
                     }
                 }else{
                     Log.e("from writing" , "can't get tag")
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

    private fun formatTag(tag :Tag?){
        if(tag == null)
            return

            val ndefTag = Ndef.get(tag)
            ndefTag.connect()
            ndefTag.writeNdefMessage(
                NdefMessage(
                    NdefRecord(
                        NdefRecord.TNF_EMPTY,
                        null,
                        null,
                        null
                    )
                )
            )
            ndefTag.close()

    }






}