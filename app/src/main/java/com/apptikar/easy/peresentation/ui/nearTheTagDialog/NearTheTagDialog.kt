package com.apptikar.easy.peresentation.ui.nearTheTagDialog

import android.nfc.Tag
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
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
import com.apptikar.easy.common.theme.LightBlue
import com.apptikar.easy.peresentation.ui.writeOnTag.InsertTheMountViewModel
import kotlinx.coroutines.delay

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun NearTheTagDialog(
    modifier: Modifier,
    navController: NavController,
    ) {

    val nfc by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.nfc_lottie))




    Dialog(
        onDismissRequest = {
             navController.popBackStack()
                           },
        properties = DialogProperties(usePlatformDefaultWidth = false),
        // then setting usePlatformDefaultWidth to false
        content = {
            Column(modifier.size(260.sdp), verticalArrangement = Arrangement.Top, Alignment.CenterHorizontally) {
                Row(modifier = Modifier.fillMaxWidth().fillMaxHeight(0.1f).background(color = LightBlue),
                    horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
                    Text(text = stringResource(R.string.insert_the_ID_number),
                        style = MaterialTheme.typography.body1,
                        fontWeight = FontWeight.Normal, fontSize = 15.textSdp, color = Color.White)
                }

            Spacer(modifier = Modifier.size(10.sdp))
                LottieAnimation(nfc, modifier = Modifier.size(150.sdp).clip(CircleShape))

                Text(text = stringResource(R.string.please_close_the_card),
                    style = MaterialTheme.typography.body1, fontWeight = FontWeight.Bold,
                    fontSize = 15.textSdp, textAlign = TextAlign.Center)
                Spacer(modifier = Modifier.size(10.sdp))
            }
        }
    )


}