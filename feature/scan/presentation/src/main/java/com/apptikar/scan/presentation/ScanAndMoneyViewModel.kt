package com.apptikar.scan.presentation

import android.content.Intent
import android.nfc.NdefMessage
import android.nfc.NfcAdapter
import android.util.Log
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.*
import com.apptikar.common.utils.ConnectivityObserver
import com.apptikar.common.utils.Event
import com.apptikar.scan.domain.model.CheckModel
import com.apptikar.scan.domain.model.MakeInvoiceModel
import com.apptikar.scan.domain.repositories.MakeInvoiceRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class ScanAndMoneyViewModel @Inject constructor(private val repository: MakeInvoiceRepo, connectivityObserver: ConnectivityObserver): ViewModel() {

     private val _cost = MutableStateFlow<String?>(null)
     val cost = _cost.asStateFlow()

    private val _code = MutableStateFlow<String?>(null)
    val code = _code.asStateFlow()
    val loading = ObservableBoolean()


    private val _navigateMakeInvoice = MutableStateFlow<MakeInvoiceModel?>(null)
    val navigateMakeInvoice = _navigateMakeInvoice.asStateFlow()

    private val _navigateCheckUser = MutableStateFlow<Event<CheckModel>?>(null)
    val navigateCheckUser = _navigateCheckUser.asStateFlow()

//    val isNetworkConnected = ObservableBoolean() ;

    private val _isNetworkConnected = MutableStateFlow(false)
    val isNetworkConnected = _isNetworkConnected.asStateFlow()


    private val _nfcContent = MutableStateFlow<Event<String>?>(null)
    val nfcContent = _nfcContent.asStateFlow()


     fun setCode(code:String?){

        _code.value = code
    }

    fun setCost(newCost:String?)
    {
      _cost.update{ newCost}
    }

    fun makeInvoiceRequest(token:String,code: String?){
        var response :Response<MakeInvoiceModel>
        loading.set(true)
        viewModelScope.launch(Dispatchers.IO){
             response =  repository.makeInvoice(token, code.toString(), cost = cost.value.toString())
            loading.set(false)
            _navigateMakeInvoice.update {  response.body()}
            Log.d("mm", response.body().toString() +" "+_code.value.toString())
        }
    }

    fun checkUser(token :String , code :String){
        var response : Response<CheckModel>
        Log.w("mm", "request pre send")
        loading.set(true);
        viewModelScope.launch(Dispatchers.IO) {
            Log.w("mm", "request send wating")
            response = repository.checkUser(token , code)
            response.body()?.code  = code
            loading.set(false)
            Log.e("checkUserResponse",response.body().toString())
            _navigateCheckUser.update{Event(response.body()) as Event<CheckModel>?}
            Log.d("mm", response.body().toString() +" "+_code.value.toString())
        }

    }

//     fun listenForNetworkConnection( context : Context? ){
//        val connectivityManager = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
//        connectivityManager.registerDefaultNetworkCallback(object : ConnectivityManager.NetworkCallback() {
//            override fun onAvailable(network: Network) {
//                //take action when network connection is gained
//                isNetworkConnected.set(true)
//            }
//
//            override fun onLost(network: Network) {
//                //take action when network connection is lost
//                isNetworkConnected.set(false)
//            }
//        })
//    }


    private fun connectivityObserver(connectivityObserver: ConnectivityObserver) {
        viewModelScope.launch {
            connectivityObserver.observe().collectLatest { status ->
                if (status == ConnectivityObserver.Status.Available) {
                    Log.d("connect","connected")
                   _isNetworkConnected.update { true }
                }else{
                    Log.d("connect","unconnected")
                    _isNetworkConnected.update { false }
                }
            }
        }

    }



     fun processIntent(checkIntent: Intent) {
        // Check if intent has the action of a discovered NFC tag
        // with NDEF formatted contents
        if (checkIntent.action == NfcAdapter.ACTION_NDEF_DISCOVERED) {
            Log.e("New NDEF intent", checkIntent.toString())

            // Retrieve the raw NDEF message from the tag
            val rawMessages = checkIntent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES)
            Log.e("Raw messages", rawMessages?.size.toString())
            Log.e("Raw messages content", rawMessages?.get(0).toString())

            // Complete variant: parse NDEF messages
            if (rawMessages != null) {
                val messages = arrayOfNulls<NdefMessage?>(rawMessages.size)// Array<NdefMessage>(rawMessages.size, {})
                for (i in rawMessages.indices) {
                    messages[i] = rawMessages[i] as NdefMessage
                }
                // Process the messages array.
                processNdefMessages(messages)
            }

        }
    }

    /**
     * Parse the NDEF message contents and print these to the on-screen log.
     */
    private fun processNdefMessages(ndefMessages: Array<NdefMessage?>) {
        // Go through all NDEF messages found on the NFC tag
        for (curMsg in ndefMessages) {
            if (curMsg != null) {
                // Print generic information about the NDEF message
                Log.e("Message", curMsg.toString())
                // The NDEF message usually contains 1+ records - print the number of records
                Log.e("Records", curMsg.records.size.toString())

                // Loop through all the records contained in the message
                for (curRecord in curMsg.records) {
                    if (curRecord.toUri() != null) {
                        // URI NDEF Tag
                        Log.e("- URI", curRecord.toUri().toString())
                    } else {
                        var stringContent = ""
                        stringContent = String(curRecord.payload!!)
                        // Other NDEF Tags - simply print the payload
                        this._nfcContent.update { (Event(stringContent.substring(3))) }
                        Log.e("- Contents",stringContent.substring(3) )
                    }
                }
            }
        }
    }

    init {
        connectivityObserver(connectivityObserver)
    }



}