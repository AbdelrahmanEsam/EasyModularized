package com.apptikar.easy.peresentation.ui.writeOnTag

import android.nfc.NdefMessage
import android.nfc.NdefRecord
import android.nfc.Tag
import android.nfc.tech.Ndef
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.lang.Exception

class InsertTheMountViewModel constructor() : ViewModel() {


    private val _mount = MutableStateFlow<String?>(null)
    val mount : StateFlow<String?> get() =  _mount

    fun setMount(mount : String){
        _mount.value = mount
    }

     fun writeToTagByNFC(tag: Tag) : Boolean{
         return try {
             val records :Array<NdefRecord> = arrayOf(createRecord(mount.value.toString()))
             val  message = NdefMessage(records)
             val ndef = Ndef.get(tag)
             ndef.connect()
             ndef.writeNdefMessage(message)
             ndef.close()

             true
         }catch (exception:Exception){
             false
         }

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