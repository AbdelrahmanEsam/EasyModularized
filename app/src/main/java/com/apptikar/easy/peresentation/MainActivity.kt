package com.apptikar.easy.peresentation

import android.app.PendingIntent
import android.content.Intent
import android.nfc.NdefMessage
import android.nfc.NdefRecord
import android.nfc.NfcAdapter
import android.nfc.Tag
import android.nfc.tech.Ndef
import android.nfc.tech.NdefFormatable
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.lifecycleScope
import androidx.window.layout.WindowInfoTracker
import androidx.window.layout.WindowLayoutInfo
import com.apptikar.dribbox.utils.rememberWindowSizeDp
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn


@AndroidEntryPoint
class MainActivity : ComponentActivity() {


    private var nfcAdapter : NfcAdapter? = null
     var tag  = MutableStateFlow<Tag?>(null)

    // Pending intent for NFC intent foreground dispatch.
    // Used to read all NDEF tags while the app is running in the foreground.
    private var nfcPendingIntent: PendingIntent? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val devicePosture = WindowInfoTracker
            .getOrCreate(this)
            .windowLayoutInfo(this)
            .stateIn(scope =lifecycleScope,
                started = SharingStarted.Eagerly,
                initialValue = WindowLayoutInfo(emptyList())
            )
        initNfc()


        setContent {
            val windowSizeDp = rememberWindowSizeDp(this)

            EasyModal(
                devicePosture =  devicePosture,
                windowSizeDp =  windowSizeDp,
            )
        }
    }

    private fun initNfc(){
        // Check if NFC is supported and enabled
        nfcAdapter = NfcAdapter.getDefaultAdapter(this)
        Log.e("NFC supported", (nfcAdapter != null).toString())
        Log.e("NFC enabled", (nfcAdapter?.isEnabled).toString())
        nfcPendingIntent = PendingIntent.getActivity(this, 0, Intent(this, javaClass).apply {
            addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
        }, PendingIntent.FLAG_MUTABLE)

        if (intent != null) {
            Log.e("Found intent in onCreate", intent.action.toString())
           processIntent(intent)
        }
    }


    override fun onResume() {
        super.onResume()
        // Get all NDEF discovered intents
        // Makes sure the app gets all discovered NDEF messages as long as it's in the foreground.
        nfcAdapter?.enableForegroundDispatch(this, nfcPendingIntent, null, null);
        // Alternative: only get specific HTTP NDEF intent
        //nfcAdapter?.enableForegroundDispatch(this, nfcPendingIntent, nfcIntentFilters, null);
    }

    override fun onPause() {
        super.onPause()
        // Disable foreground dispatch, as this activity is no longer in the foreground
        nfcAdapter?.disableForegroundDispatch(this);
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        Log.e("Found intent in onNewIntent", intent?.action.toString())
        if (intent != null) processIntent(intent)
    }


    fun processIntent(checkIntent: Intent) {

//        if (checkIntent.action == NfcAdapter.ACTION_NDEF_DISCOVERED) {
//            Log.e("New NDEF intent", checkIntent.toString())
//            var data  = null ;
//            if (Build.VERSION.SDK_INT >= 33) {
//                 data = checkIntent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES , Tag::class.java)?.get(0) as Nothing?
//            }else{
//               var data = checkIntent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES)
//                if (data != null) {
//                    val messages = arrayOfNulls<NdefMessage?>(data.size)// Array<NdefMessage>(rawMessages.size, {})
//                    for (i in data.indices) {
//                        messages[i] = data[i] as NdefMessage
//                    }
//                    // Process the messages array.
//                    processNdefMessages(messages)
//                }
//            }
//
//
//        }

        if(checkIntent.action == NfcAdapter.ACTION_TAG_DISCOVERED){
           tag   = checkIntent.getParcelableExtra(NfcAdapter.EXTRA_TAG)!!
            val techList = tag.value?.techList
            Log.e("tag techs : " , techList.toString())

//            Log.e("our Tag is :" , tag.toString())
        }

        }

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
                        Log.e("- Contents",stringContent.substring(3) )
                    }
                }
            }
        }
    }

    fun writeToTagByNFC(tag : Tag?) : Boolean{
        Log.e("fromMainActivity ; ", tag.toString());
        try {
            val records :Array<NdefRecord> = arrayOf(createRecord("545461235"))
            val  message = NdefMessage(records)
            val ndef = Ndef.get(tag)
            ndef.connect()
            ndef.writeNdefMessage(message)
            ndef.close()

            return true
        }catch (exception: Exception){
            Log.e("catch some error (mainactivity): " , exception.toString() );
            return  false
        }

    }

    fun formatTag(tag : Tag?){
        val ndefFormatable = NdefFormatable.get(tag)
    if (ndefFormatable != null) {
        try {
            ndefFormatable.connect();
            val records :Array<NdefRecord> = arrayOf(createRecord("545461235"))
            val  message = NdefMessage(records);
            ndefFormatable.format(message);
        } finally {
            try {
                ndefFormatable.close();
            } catch (exception: Exception ) {
                Log.e("Formating message : " , exception.toString())
            }
        }
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


