package com.apptikar.easy.peresentation.ui.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.End
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.apptikar.dribbox.utils.sdp
import com.apptikar.dribbox.utils.textSdp
import com.apptikar.easy.R
import com.apptikar.easy.common.theme.BoxBackGroundColor
import com.apptikar.easy.common.theme.Gray
import com.apptikar.easy.common.theme.LightBlue


@Composable
fun Login(
    modifier: Modifier,
    loginViewModel: LoginViewModel = hiltViewModel()

) {

   val code =  loginViewModel.code.collectAsState()
   val password = loginViewModel.password.collectAsState()
    val height  = LocalConfiguration.current.screenHeightDp
    val width = LocalConfiguration.current.screenWidthDp
    Column(modifier = modifier
        .padding(horizontal = (width * 0.05).dp)
        , verticalArrangement = Arrangement.Top, horizontalAlignment = End
    ) {

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
            textStyle = TextStyle(fontSize = 10.textSdp),
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
            textStyle = TextStyle(fontSize = 10.textSdp),
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
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
            )
        Spacer(modifier = Modifier.height(25.sdp))
        TextButton(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .background(
                    color = LightBlue,
                    shape = RoundedCornerShape(10.sdp)
                )
                .padding(horizontal = 10.dp).fillMaxWidth().height(45.sdp)

        ) {
            Text(text = stringResource(R.string.login), style =  MaterialTheme.typography.body1, fontSize = 12.textSdp, color = White)
        }
    }
    Spacer(modifier = Modifier.height(25.sdp))
}