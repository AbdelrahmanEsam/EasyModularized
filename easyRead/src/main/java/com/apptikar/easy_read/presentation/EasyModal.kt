package com.apptikar.easy_read.presentation

import android.nfc.Tag
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.DpSize
import androidx.navigation.compose.rememberNavController
import androidx.window.layout.WindowLayoutInfo
import com.apptikar.common.theme.EasyTheme
import com.apptikar.common.utils.ConnectivityObserver
import com.apptikar.common.utils.ScreenInfo
import com.apptikar.easy_read.presentation.navigation.EasyNavGraph
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@Composable
fun EasyModal(
    devicePosture: StateFlow<WindowLayoutInfo>,
    windowSizeDp: DpSize,
    connectivityStatus: ConnectivityObserver.Status,
) {
    val devicePostureValue by devicePosture.collectAsState()

    EasyTheme {
        val screenClassifier = ScreenInfo().screenClassifier(windowDpSize =windowSizeDp, devicePosture = devicePostureValue)
        val navController = rememberNavController()
        val scaffoldState = rememberScaffoldState()

        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            Scaffold(
                scaffoldState = scaffoldState,
            ){paddingValues ->

                Row(Modifier.fillMaxSize()) {
                    EasyNavGraph(connectivityStatus = connectivityStatus,screenClassifier = screenClassifier,
                        navController = navController ,
                        modifier = Modifier.padding(paddingValues), scaffoldState = scaffoldState,
                    )

                }

            }
        }
    }

}