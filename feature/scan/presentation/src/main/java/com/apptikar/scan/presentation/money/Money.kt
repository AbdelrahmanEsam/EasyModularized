package com.apptikar.scan.presentation.money

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension.Companion.fillToConstraints
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavHostController
import com.apptikar.common.utils.readToken
import com.apptikar.common.utils.sdp
import com.apptikar.common.utils.textSdp
import com.apptikar.scan.presentation.ScanAndMoneyViewModel
import com.apptikar.scan.presentation.done.DoneDialog
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.apptikar.scan.presentation.R

@Composable
fun Money(
    modifier: Modifier,
    navController: NavHostController,
    scanAndMoneyViewModel: ScanAndMoneyViewModel = hiltViewModel()

) {
    val context = LocalContext.current
    val isNetworkConnected = scanAndMoneyViewModel.isNetworkConnected.collectAsState()
    val cost = scanAndMoneyViewModel.cost.collectAsState()
    val showDoneDialog = rememberSaveable { mutableStateOf(false) }
    val rememberCoroutineScope = rememberCoroutineScope()
    val lifeCycleOwner = LocalLifecycleOwner.current

    listenToFragmentCallBack(navController,lifeCycleOwner)
    navigateObserver(navController,scanAndMoneyViewModel,context,rememberCoroutineScope)


    if (showDoneDialog.value){
        DoneDialog(modifier = Modifier
            .clip(RoundedCornerShape(10.sdp))
            .background(Color.White)
            , navController = navController, setShowDialog = {  show ->
                showDoneDialog.value = show
            })
    }

    ConstraintLayout(modifier = modifier) {

        val (backButton,wantedMoneyText,pleaseEnterThePriceText,priceText,
            priceInputLayout,confirmButton) = createRefs()

        Image(modifier = Modifier
            .padding(end = 20.sdp, top = 20.sdp)
            .size(30.sdp)
            .constrainAs(backButton) {
                top.linkTo(parent.top)
                end.linkTo(parent.end)
            }
            .clickable {
                navController.popBackStack()
            }
            , painter = painterResource(id = R.drawable.ic_back), contentDescription = stringResource(id = R.string.logout))



        Text(
            modifier = Modifier
                .padding(top = 30.sdp)
                .constrainAs(wantedMoneyText) {
                    top.linkTo(backButton.bottom)
                    end.linkTo(backButton.end)
                }
                .padding(horizontal = 20.sdp),
            text = stringResource(id = R.string.wanted_money),
            style = MaterialTheme.typography.h4
        )

        Text(
            modifier = Modifier
                .padding(top = 20.sdp)
                .constrainAs(pleaseEnterThePriceText) {
                    top.linkTo(wantedMoneyText.bottom)
                    end.linkTo(wantedMoneyText.end)
                }
                .padding(horizontal = 20.sdp),
            text = stringResource(id = R.string.the_price),
            style = MaterialTheme.typography.h5
        )

        Text(
            modifier = Modifier
                .padding(top = 20.sdp)
                .constrainAs(priceText) {
                    top.linkTo(pleaseEnterThePriceText.bottom)
                    end.linkTo(pleaseEnterThePriceText.end)
                }
                .padding(horizontal = 20.sdp),
            text = stringResource(id = R.string.price),
            style = MaterialTheme.typography.h5
        )


        TextField(
            modifier = Modifier
                .padding(top = 9.sdp)
                .constrainAs(priceInputLayout) {
                    top.linkTo(priceText.bottom)
                    end.linkTo(priceText.end)
                }
                .padding(start = 15.sdp, end = 15.sdp)
                .fillMaxWidth()
                .height(45.sdp),
            value =  cost.value ?: "" ,
           onValueChange = {
                       scanAndMoneyViewModel.setCost(it)
           },
            textStyle = TextStyle(fontSize = 10.textSdp, textAlign = TextAlign.Right, color = Color.Black),
            shape = RoundedCornerShape(topStart = 8.sdp , topEnd = 8.sdp),
            maxLines = 1,
            singleLine = true,
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.White,
                disabledIndicatorColor = Color.Black,
                focusedIndicatorColor = Color.Black,
                unfocusedIndicatorColor = Color.Black
            ),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )


        Row(modifier = Modifier
            .padding(top = 50.sdp)
            .height(45.sdp)
            .constrainAs(confirmButton) {
                top.linkTo(priceInputLayout.bottom)
                start.linkTo(priceInputLayout.start)
                end.linkTo(priceInputLayout.end)
                width = fillToConstraints
            }
            .padding(start = 15.sdp, end = 15.sdp)
            .clip(RoundedCornerShape(10.sdp))
            .background(colorResource(id = R.color.primary_color))
            .clickable {
//                confirm(isNetworkConnected, context, rememberCoroutineScope, scanAndMoneyViewModel)
                showDoneDialog.value = true
            }, horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically){
            Text(
                modifier = Modifier,
                text = stringResource(id = R.string.confirm_text),
                style = MaterialTheme.typography.h5,
                color = Color.White
            )
        }
    }

}

//
//@Preview(device = Devices.PIXEL_4)
//@Composable
//fun MoneyPrev() {
//Money(modifier = Modifier
//    .fillMaxSize()
//    .background(Color.White)
//    .verticalScroll(
//        rememberScrollState()
//    ))
//}

private fun confirm(
    isNetworkConnected: State<Boolean?>,
    context: Context,
    rememberCoroutineScope: CoroutineScope,
    scanAndMoneyViewModel: ScanAndMoneyViewModel
) {

        if (!isNetworkConnected.value!!){
            Toast.makeText(
                context,
                context.resources.getString(R.string.please_connect_to_internet),
                Toast.LENGTH_LONG
            ).show()
            return
        }


        if (!scanAndMoneyViewModel.cost.value.isNullOrEmpty()){

            rememberCoroutineScope.launch(Dispatchers.IO){
               val token = context.readToken("tokenKey")
//                scanAndMoneyViewModel.makeInvoiceRequest(token!!,args.code)
            }

        }
    }


private fun listenToFragmentCallBack(navController: NavHostController,viewLifecycleOwner:LifecycleOwner)
{
    navController.currentBackStackEntry?.savedStateHandle?.getLiveData<String>("key")?.observe(
        viewLifecycleOwner) {
        navController.popBackStack()
    }

}
private  fun navigateObserver(
    navController: NavHostController,
    scanAndMoneyViewModel: ScanAndMoneyViewModel,
    context: Context,
    rememberCoroutineScope: CoroutineScope
){
    rememberCoroutineScope.launch(Dispatchers.Main) {
        scanAndMoneyViewModel.navigateMakeInvoice.collect {
            if (it?.status == 1) {
//            navController.navigate(MoneyFragmentDirections.actionMoneyFragmentToDoneDialogFragment())
            } else {
                Toast.makeText(context, it?.message ?: "", Toast.LENGTH_SHORT).show()
            }
        }
    }


    }


