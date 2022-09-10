package com.apptikar.easy.peresentation.ui.login

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.End
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.unit.dp
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.navigation.*
import com.apptikar.dribbox.utils.sdp
import com.apptikar.dribbox.utils.textSdp
import com.apptikar.easy.R
import com.apptikar.easy.common.theme.BoxBackGroundColor
import com.apptikar.easy.common.theme.Gray
import com.apptikar.easy.common.theme.LightBlue
import com.apptikar.easy.common.utils.ConnectivityObserver
import com.apptikar.easy.common.utils.saveToken
import com.apptikar.easy.data.dto.DataX
import com.apptikar.easy.peresentation.MainActivity
import com.apptikar.easy.peresentation.navigation.Destinations
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@Composable
fun Login(
    modifier: Modifier,
    loginViewModel: LoginViewModel = hiltViewModel(),
    navController: NavHostController
) {

   val code =  loginViewModel.code.collectAsState()
   val password = loginViewModel.password.collectAsState()
    val userInfo = loginViewModel.userInfo.collectAsState()
    val connectivityStatus = (LocalContext.current as MainActivity).connectivityStatus
    val toastMessage = rememberSaveable { mutableStateOf(0) }
    val showToast = rememberSaveable { mutableStateOf(false) }




    val height  = LocalConfiguration.current.screenHeightDp
    val width = LocalConfiguration.current.screenWidthDp
    val context =  LocalContext.current
    Column(modifier = modifier
        .padding(horizontal = (width * 0.05).dp)
        , verticalArrangement = Arrangement.Top, horizontalAlignment = End
    ) {

        LaunchedEffect(key1 = userInfo.value){
            Log.d("abdo","success")
            if (userInfo.value?.status == 1 ) {
                this.launch(Dispatchers.IO) {
                   context.saveToken("tokenKey", userInfo.value!!.data.token)
                }

                navController.navigate(Destinations.InsertTheMount)


            } else {
                    Toast.makeText(
                        context,
                         userInfo.value?.message,
                        Toast.LENGTH_LONG
                    ).show()
            }
        }


        if (showToast.value){
            Toast.makeText(LocalContext.current, stringResource(id = toastMessage.value), Toast.LENGTH_LONG).show()
            showToast.value = false
        }

        Spacer(modifier = Modifier.height((height * 0.1).dp))
        Image(
            painter = painterResource(id = R.drawable.easy),
            contentDescription = stringResource(R.string.logo),
            modifier = Modifier
                .size(75.sdp)
                .background(Color.White)
                .align(CenterHorizontally)

        )
        Spacer(modifier = Modifier.height(20.sdp))
        Text(text = stringResource(R.string.welcome_back), style = MaterialTheme.typography.body1, fontWeight = FontWeight.Bold, fontSize = 20.textSdp)
        Spacer(modifier = Modifier.height(20.sdp))
        Text(text = stringResource(R.string.code), style = MaterialTheme.typography.body1, fontWeight = FontWeight.Normal, fontSize = 12.textSdp, color = Gray)
        Spacer(modifier = Modifier.height(10.sdp))
        TextField(
            value = code.value ?: "" ,
            onValueChange = {  loginViewModel.setCode(it) },
            modifier = Modifier
                .fillMaxWidth()
                .height(45.sdp),
            textStyle = TextStyle(fontSize = 10.textSdp, textAlign = TextAlign.Right),
            shape = RoundedCornerShape(topStart = 8.sdp , topEnd = 8.sdp),
            maxLines = 1,
            singleLine = true,
            colors = TextFieldDefaults.textFieldColors(
             backgroundColor = BoxBackGroundColor,
                disabledIndicatorColor = Black,
                focusedIndicatorColor = Black,
                unfocusedIndicatorColor = Black
                ),
            placeholder = {
                Row(
                    modifier = Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End
                ){

                    Text(text = stringResource(R.string.insert_code),
                        style = MaterialTheme.typography.body1,
                        color = Gray,
                        modifier = Modifier
                            .wrapContentSize()
                            .offset(x = (-2).dp),
                        fontSize = 10.textSdp)
                }

            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)

        )
        Spacer(modifier = Modifier.height(20.sdp))
        Text(text = stringResource(R.string.password), style = MaterialTheme.typography.body1, fontWeight = FontWeight.Normal, fontSize = 12.textSdp, color = Gray)
        Spacer(modifier = Modifier.height(10.sdp))
        TextField(
            value = password.value ?: "" ,
            onValueChange = {  loginViewModel.setPassword(it) },
            modifier = Modifier
                .fillMaxWidth()
                .height(45.sdp),
            textStyle = TextStyle(fontSize = 10.textSdp, textAlign = TextAlign.Right),
            shape = RoundedCornerShape(topStart = 8.sdp , topEnd = 8.sdp),
            maxLines = 1,
            singleLine = true,
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = BoxBackGroundColor,
                disabledIndicatorColor = Black,
                focusedIndicatorColor = Black,
                unfocusedIndicatorColor = Black
            ),

            placeholder = {
                Row(
                    modifier = Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End
                ){

                    Text(text = stringResource(R.string.insert_password),
                        style = MaterialTheme.typography.body1,
                        color = Gray,
                        modifier = Modifier
                            .wrapContentSize()
                            .offset(x = (-2).dp),
                    fontSize = 10.textSdp
                        )
                }

            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = PasswordVisualTransformation(),

            )
        Spacer(modifier = Modifier.height(25.sdp))
        TextButton(
            onClick = {
                if (connectivityStatus == ConnectivityObserver.Status.Lost){
                    toastMessage.value =  R.string.connectivity_lost
                    showToast.value = true
                    return@TextButton
                }

                loginViewModel.login()

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
            Text(text = stringResource(R.string.login), style =  MaterialTheme.typography.body1, fontSize = 12.textSdp, color = White)
        }
    }
    Spacer(modifier = Modifier.height(25.sdp))
}


