package com.apptikar.easy.peresentation.ui.doneDialog

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.apptikar.dribbox.utils.sdp
import com.apptikar.dribbox.utils.textSdp
import com.apptikar.easy.R
import kotlinx.coroutines.delay


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun DoneDialog(modifier: Modifier, navController: NavController,setShowDialog: (Boolean) -> Unit) {
    val done by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.animated_done))

    LaunchedEffect(true){
        delay(2000L)
        setShowDialog(false)
    }


    Dialog(
        onDismissRequest = { setShowDialog(false) ; navController.popBackStack() },
        properties = DialogProperties(usePlatformDefaultWidth = false),
        // then setting usePlatformDefaultWidth to false
        content = {
            Column(modifier.size(260.sdp), verticalArrangement = Arrangement.Top,Alignment.CenterHorizontally) {
                LottieAnimation(done, modifier = Modifier.size(150.sdp).clip(CircleShape))
                Text(text = stringResource(R.string.Well), style = MaterialTheme.typography.body1, fontWeight = FontWeight.Bold, fontSize = 25.textSdp, modifier = Modifier.offset(y = ((-25).sdp)))
//            Spacer(modifier = Modifier.size(10.sdp))
                Text(text = stringResource(R.string.done_successfully), style = MaterialTheme.typography.body1, fontWeight = FontWeight.Bold, fontSize = 15.textSdp, textAlign = TextAlign.Center)
                Spacer(modifier = Modifier.size(10.sdp))
            }
        }
    )


}