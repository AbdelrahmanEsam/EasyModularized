package com.apptikar.writeontag.navigation

import android.nfc.Tag
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.*
import androidx.navigation.compose.composable
import com.apptikar.common.Destinations
import com.apptikar.common.utils.ConnectivityObserver
import com.apptikar.common.utils.sdp
import com.apptikar.writeontag.presentation.insertTheMount.InsertTheMount
import kotlinx.coroutines.flow.MutableStateFlow

fun NavController.navigateToWriteOnTag(navOptions: NavOptions? = null) {
    this.navigate(Destinations.login, navOptions)
}

fun NavGraphBuilder.writeOnTagScreen(
    navController: NavHostController,
    connectivityStatus: ConnectivityObserver.Status,
    tag: MutableStateFlow<Tag?>

) {
    composable(  route = Destinations.InsertTheMount,
        deepLinks = listOf(navDeepLink { uriPattern = "sahl://InsertTheMount" })) {
        InsertTheMount(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .verticalScroll(
                    rememberScrollState()
                ).padding(horizontal = 10.sdp),
            navController= navController,
            connectivityStatus =   connectivityStatus,
            tag = tag
        )
    }
}