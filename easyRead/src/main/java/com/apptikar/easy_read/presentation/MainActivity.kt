package com.apptikar.easy_read.presentation

import android.app.PendingIntent
import android.content.Intent
import android.nfc.NfcAdapter
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.lifecycleScope
import androidx.window.layout.WindowInfoTracker
import androidx.window.layout.WindowLayoutInfo
import com.apptikar.common.utils.ConnectivityObserver
import com.apptikar.common.utils.rememberWindowSizeDp
import com.apptikar.easy.R
import com.apptikar.scan.presentation.ScanAndMoneyViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private var nfcAdapter : NfcAdapter? = null

    @Inject
    lateinit var connectivityObserver: ConnectivityObserver
    // Pending intent for NFC intent foreground dispatch.
    // Used to read all NDEF tags while the app is running in the foreground.
    private var nfcPendingIntent: PendingIntent? = null

    private val viewModel: ScanAndMoneyViewModel by viewModels()
    var connectivityStatus: ConnectivityObserver.Status =  ConnectivityObserver.Status.Available

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        connectivityObserver(connectivityObserver)

        val devicePosture = WindowInfoTracker
            .getOrCreate(this)
            .windowLayoutInfo(this)
            .stateIn(
                scope = lifecycleScope,
                started = SharingStarted.Eagerly,
                initialValue = WindowLayoutInfo(emptyList())
            )

        setContent {
            val windowSizeDp = rememberWindowSizeDp(this)

            EasyModal(
                connectivityStatus = connectivityStatus,
                devicePosture = devicePosture,
                windowSizeDp = windowSizeDp,
            )
        }
       initNfc()

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }

    private fun connectivityObserver(connectivityObserver: ConnectivityObserver) {
        lifecycleScope.launch {
            connectivityObserver.observe().collectLatest { status ->
                connectivityStatus = status
                Log.d("status20",connectivityStatus.name)
                if (status == ConnectivityObserver.Status.Lost ) {
                    Toast.makeText(
                        this@MainActivity,
                        getString(R.string.connectivity_lost),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
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
            viewModel.processIntent(intent)
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
        if (intent != null) viewModel.processIntent(intent)
    }






}