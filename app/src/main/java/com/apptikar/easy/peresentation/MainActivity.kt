package com.apptikar.easy.peresentation

import android.app.PendingIntent
import android.content.Intent
import android.nfc.NfcAdapter
import android.nfc.Tag
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.lifecycleScope
import androidx.window.layout.WindowInfoTracker
import androidx.window.layout.WindowLayoutInfo
import com.apptikar.dribbox.utils.rememberWindowSizeDp
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

@AndroidEntryPoint
class MainActivity : ComponentActivity() {


    private var nfcAdapter : NfcAdapter? = null
    private var tag : Tag? = null

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
                tag
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
        // If we got an intent while the app is running, also check if it's a new NDEF message
        // that was discovered
        if (intent != null) processIntent(intent)
    }


    fun processIntent(checkIntent: Intent) {
        // Check if intent has the action of a discovered NFC tag
        // with NDEF formatted contents
        if (checkIntent.action == NfcAdapter.ACTION_NDEF_DISCOVERED) {
            Log.e("New NDEF intent", checkIntent.toString())
            tag = if (Build.VERSION.SDK_INT >= 33) {
                checkIntent.getParcelableArrayExtra(NfcAdapter.EXTRA_TAG,Tag::class.java)?.get(0)
            }else{
                checkIntent.getParcelableArrayExtra(NfcAdapter.EXTRA_TAG)?.get(0) as Tag?
            }

        }

        }
    }


