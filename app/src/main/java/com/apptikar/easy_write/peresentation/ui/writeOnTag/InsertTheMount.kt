package com.apptikar.easy_write.peresentation.ui.writeOnTag

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.apptikar.dribbox.utils.sdp
import com.apptikar.dribbox.utils.textSdp
import com.apptikar.easy_write.R
import com.apptikar.easy_write.common.theme.BoxBackGroundColor
import com.apptikar.easy_write.common.theme.Gray
import com.apptikar.easy_write.common.theme.LightBlue
import com.apptikar.easy_write.common.utils.ConnectivityObserver
import com.apptikar.easy_write.common.utils.navigateToInclusive
import com.apptikar.easy_write.common.utils.readToken
import com.apptikar.easy_write.common.utils.saveToken
import com.apptikar.easy_write.peresentation.MainActivity
import com.apptikar.easy_write.peresentation.navigation.Destinations
import com.apptikar.easy_write.peresentation.ui.doneDialog.DoneDialog
import com.apptikar.easy_write.peresentation.ui.nearTheTagDialog.NearTheTagDialog
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


@Composable
fun InsertTheMount(
    modifier: Modifier,
    navController: NavHostController,
    writeOnTagViewModel: InsertTheMountViewModel = hiltViewModel(),

    ) {
    val mount =  writeOnTagViewModel.userId.collectAsState()
    val showTagDialog = rememberSaveable { mutableStateOf(false) }
    val showDoneDialog = rememberSaveable { mutableStateOf(false) }
    val showToast = rememberSaveable { mutableStateOf(false) }
    val toastMessage =rememberSaveable { mutableStateOf(0) }
    val tag =  (LocalContext.current as MainActivity).tag
    val connectivityStatus = (LocalContext.current as MainActivity).connectivityStatus
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current

    Box(contentAlignment = Alignment.Center) {
        Column(
            modifier = modifier,
            verticalArrangement = Arrangement.Top,
            Alignment.End
        ) {


            if (showTagDialog.value){

                NearTheTagDialog(modifier = Modifier
                    .clip(RoundedCornerShape(10.sdp))
                    .background(Color.White),
                   setShowDialog = {  show ->
                       showTagDialog.value = show
                   }
                )


            }


            if (showDoneDialog.value){
                DoneDialog(modifier = Modifier
                    .clip(RoundedCornerShape(10.sdp))
                    .background(Color.White)
                    , navController = navController, setShowDialog = {  show ->
                        showDoneDialog.value = show
                    })
                writeOnTagViewModel.setUserId("")
            }
            if (showToast.value){
                Toast.makeText(LocalContext.current, stringResource(id = toastMessage.value), Toast.LENGTH_LONG).show()
                showToast.value = false
            }

            Spacer(modifier = Modifier.size(30.sdp))
            Image(
                painter = painterResource(id = R.drawable.ic_back),
                contentDescription = stringResource(R.string.back),
                modifier = Modifier
                    .size(25.sdp)
                    .background(Color.White)
                    .clickable {
                        coroutineScope.launch {
                            launch(Dispatchers.IO) {
                                context.saveToken("tokenKey", "")
                            }
                        }
                        navController.navigateToInclusive(Destinations.Login)
                    }

            )
            Spacer(modifier = Modifier.size(50.sdp))
            Text(text = stringResource(R.string.insert_the_ID_number), style = MaterialTheme.typography.body1, fontWeight = FontWeight.Bold, fontSize = 20.textSdp)
            Spacer(modifier = Modifier.height(20.sdp))
            Text(text = stringResource(R.string.ID_number), style = MaterialTheme.typography.body1, fontWeight = FontWeight.Normal, fontSize = 12.textSdp, color = Gray)
            Spacer(modifier = Modifier.height(5.sdp))
            TextField(
                value = mount.value ?: "" ,
                onValueChange = {  writeOnTagViewModel.setUserId(it) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(45.sdp),
                textStyle = TextStyle(fontSize = 10.textSdp, color = Color.Black),
                shape = RoundedCornerShape(topStart = 8.sdp , topEnd = 8.sdp),
                maxLines = 1,
                singleLine = true,
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = BoxBackGroundColor,
                    disabledIndicatorColor = Color.Black,
                    focusedIndicatorColor = Color.Black,
                    unfocusedIndicatorColor = Color.Black
                ),
                placeholder = {
                    Row(
                        modifier = Modifier.fillMaxSize(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.End
                    ){

                        Text(text = stringResource(R.string.ID_number),
                            style = MaterialTheme.typography.body1,
                            color = Gray,
                            modifier = Modifier
                                .wrapContentSize()
                                .offset(x = (-2).dp),
                            fontSize = 10.textSdp)
                    }

                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)

            )

            Spacer(modifier = Modifier.height(100.sdp))
            TextButton(
                onClick = {
                    if (connectivityStatus != ConnectivityObserver.Status.Available){
                        Log.d("statusInsert",connectivityStatus.name)
                        toastMessage.value =  R.string.connectivity_lost
                        showToast.value = true
                    }else{
                        if (mount.value.isNullOrEmpty()){
                            toastMessage.value =  R.string.please_insert_the_ID_number
                            showToast.value = true
                        }else{
                            coroutineScope.launch {
                                val token : String? = context.readToken("tokenKey")
                                writeOnTagViewModel.getUserById(token)
                            }
                            coroutineScope.launch {
                                writeOnTagViewModel.response.collectLatest {
                                   if(it?.status == 1 ){
                                            showTagDialog.value = true
                                            tag.collectLatest {
                                                Log.e("there is an tag upproch " , it.toString())
                                                if (it != null){
                                                    Log.e("for lisener" , "tag is not null")
                                                    val writeSucceed = writeOnTagViewModel.writeToTagByNFC(it)
                                                    Log.e("for lisener" , writeSucceed.toString())
                                                    if(writeSucceed.not()){
                                                            toastMessage.value =
                                                                R.string.card_is_already_written
                                                            showToast.value = true
                                                    }
                                                    showTagDialog.value = writeSucceed.not()
                                                    showDoneDialog.value  = writeSucceed
                                                    if(writeSucceed) {
                                                        writeOnTagViewModel.setUserId(null)
                                                        writeOnTagViewModel.setCode(null)
                                                        tag.update { null }
                                                        writeOnTagViewModel.emptyResponse()
                                                    }
                                                }
                                            }
                                    }else{
                                        if(it != null ) {
                                            toastMessage.value = R.string.there_is_no_member
                                            showToast.value = true
                                        }
                                   }
                                }
                            }
                        }
                    }



                },
                modifier = Modifier
                    .background(
                        color = LightBlue,
                        shape = RoundedCornerShape(10.sdp)
                    )
                    .padding(horizontal = 10.dp)
                    .fillMaxWidth()
                    .height(45.sdp)

            ) {
                Text(text = stringResource(R.string.confirm), style =  MaterialTheme.typography.body1, fontSize = 12.textSdp, color = Color.White)
            }
            Spacer(modifier = Modifier.height(30.sdp))


        }

//        CircularProgressIndicator(
//            color = LightBlue,
//            modifier = Modifier.then(Modifier.size(if (writeOnTagViewModel.isLoading.value){60.sdp}else{0.sdp})),
//           strokeWidth = 4.sdp
//        )
    }


}







