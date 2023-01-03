package com.apptikar.scan.presentation.scan

import android.media.MediaPlayer
import android.widget.Toast
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.tween
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.apptikar.common.Destinations
import com.apptikar.common.utils.readToken
import com.apptikar.common.utils.sdp
import com.apptikar.scan.presentation.R
import com.apptikar.scan.presentation.ScanAndMoneyViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@Composable
fun Scan(
    modifier:Modifier,
    navController: NavHostController,
    scanAndMoneyViewModel: ScanAndMoneyViewModel = hiltViewModel(),
) {

    val navigationState by scanAndMoneyViewModel.navigateCheckUser.collectAsState()
    val isNetworkConnected by scanAndMoneyViewModel.isNetworkConnected.collectAsState()
    val codeFromNFC by scanAndMoneyViewModel.nfcContent.collectAsState()
    val loading by scanAndMoneyViewModel.loading.collectAsState()
    var contentFromNFC:String? = null
    var blueCircleState by rememberSaveable { mutableStateOf(CircleState.SmallCircle) }
    var mobileVisibility by rememberSaveable { mutableStateOf(Visibility.Visible) }
    var doneCardVisibility by rememberSaveable { mutableStateOf(Visibility.Invisible) }
    val context = LocalContext.current
    var mediaPlayer : MediaPlayer? = null
    val coroutineScope = rememberCoroutineScope()

    DisposableEffect(key1 = isNetworkConnected, key2 = navigationState){
        navigationState?.getContentIfNotHandled()?.let {
            if (it.status == 1){

                // transition animation and navigate after complete
                blueCircleState = CircleState.BigCircle
                mobileVisibility = Visibility.Invisible
                doneCardVisibility = Visibility.Visible
                if (mediaPlayer == null){
                    mediaPlayer = MediaPlayer.create(context, R.raw.sound_effect)
                }

                mediaPlayer?.start()
                mediaPlayer?.setOnCompletionListener {
                    mediaPlayer?.release()
                }

            }else{
                if (mediaPlayer == null){
                    mediaPlayer = MediaPlayer.create(context, R.raw.sound_effect)
                }
                mediaPlayer?.start()
                mediaPlayer?.setOnCompletionListener {
                    mediaPlayer?.release()
                }
                Toast.makeText(context, context.resources.getString(R.string.this_member_is_not_registered), Toast.LENGTH_SHORT).show()

            }


        }
        onDispose {
               mediaPlayer = null
        }

    }
    LaunchedEffect(key1 = codeFromNFC){
        codeFromNFC?.getContentIfNotHandled().let {
            if(it != null ) {
                contentFromNFC = it
            }
        }
    }
    if (contentFromNFC != null) {
        LaunchedEffect(key1 = isNetworkConnected) {
            this.launch(Dispatchers.Main) {
                if (!loading!!) {
                    val token = context.readToken("tokenKey")
                    scanAndMoneyViewModel.checkUser(token!!, contentFromNFC!!)
                }
            }
        }
    }

//    navigateObserver(navigationState,mediaPlayer)

    ConstraintLayout(modifier = modifier) {
        val (logoutButton,readText,lightBlueCircle,blueCircle,
            mobileImage,doneCard,readCardDescriptionText) = createRefs()
        val verticalGuidelineFifePercent = createGuidelineFromEnd(0.05f)

          val blueCircleDp by animateDpAsState(targetValue = if (blueCircleState == CircleState.SmallCircle) 150.sdp else 180.sdp,
          tween(durationMillis = 200)
          )
        val mobileAlpha by animateFloatAsState(targetValue = if (mobileVisibility == Visibility.Visible)  1f else 0f,
            tween(durationMillis = 200),
            finishedListener = {
                navController.navigate(Destinations.Money)
            }
            )


        val doneCardPositionAnimation by animateDpAsState(targetValue = if (doneCardVisibility == Visibility.Invisible) 0.sdp else 1.sdp  ,
            keyframes {
                5.dp.at(100)
                (-5).dp.at(140)
                 5.dp.at(180)
                durationMillis = 200
            },
        )
        val doneCardAlpha by animateFloatAsState(targetValue = if (doneCardVisibility == Visibility.Invisible)  0f else 1f,
            keyframes {
                0f.at(100)
                durationMillis = 200
            }
        )




        Image(modifier = Modifier
            .padding(start = 12.sdp, top = 12.sdp)
            .constrainAs(logoutButton) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
            }
            .clickable {
                navController.navigate(Destinations.Money)
//                coroutineScope.launch(Dispatchers.IO) {
//                    context.saveToken("tokenKey", "")
//                }
            }
           , painter = painterResource(id = R.drawable.ic_logout), contentDescription = stringResource(id = R.string.logout))

            Text(
                modifier = Modifier
                    .padding(top = 50.sdp)
                    .constrainAs(readText) {
                        top.linkTo(parent.top)
                        end.linkTo(verticalGuidelineFifePercent)
                    },
                text = stringResource(id = R.string.read_card),
                style = MaterialTheme.typography.h4
            )
                    Row(
                        modifier = Modifier
                            .constrainAs(lightBlueCircle) {
                                top.linkTo(readText.bottom)
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                            }
                            .padding(vertical = 80.sdp)
                            .size(220.sdp)
                            .clip(CircleShape)
                            .background(color = colorResource(R.color.light_blue))
                    ) {}
                    Row(
                        modifier = Modifier
                            .constrainAs(blueCircle) {
                                top.linkTo(lightBlueCircle.top)
                                bottom.linkTo(lightBlueCircle.bottom)
                                start.linkTo(lightBlueCircle.start)
                                end.linkTo(lightBlueCircle.end)
                            }
                            .size(blueCircleDp)
                            .clip(CircleShape)
                            .background(
                                color = colorResource(id = R.color.blue),
                                shape = CircleShape
                            )
                    ) {}

        Image(
            modifier = Modifier
                .constrainAs(doneCard) {
                    top.linkTo(blueCircle.top)
                    start.linkTo(lightBlueCircle.start)
                    end.linkTo(lightBlueCircle.end)
                    bottom.linkTo(blueCircle.bottom)
                }
                .padding(30.sdp)
                .height(75.sdp)
                .width(140.sdp)
                .absoluteOffset(y = doneCardPositionAnimation)
                .alpha(doneCardAlpha)
                ,
            painter = painterResource(id = R.drawable.ic_done_card),
            contentDescription = "doneCard"
        )


            Image(
                modifier = Modifier
                    .constrainAs(mobileImage) {
                        top.linkTo(blueCircle.top)
                        start.linkTo(lightBlueCircle.start)
                        end.linkTo(lightBlueCircle.end)
                    }
                    .padding(30.sdp)
                    .height(185.sdp)
                    .width(125.sdp)
                    .alpha(mobileAlpha),
                painter = painterResource(id = R.drawable.ic_mobile),
                contentDescription = "mobile"
            )




        Text(
            modifier = Modifier
                .constrainAs(readCardDescriptionText) {
                    top.linkTo(mobileImage.bottom)
                    start.linkTo(lightBlueCircle.start)
                    end.linkTo(lightBlueCircle.end)
                }
                .padding(horizontal = 20.sdp),
            text = stringResource(id = R.string.read_card_description),
            style = MaterialTheme.typography.h5
        )

            }





    }





    
    


//@Preview(device = Devices.PIXEL_4)
//@Composable
//fun ScanPrev() {
//Scan(modifier = Modifier
//    .fillMaxSize()
//    .background(Color.White)
//    .verticalScroll(
//        rememberScrollState()
//    ))
//}