package com.example.tasktodo.presentation.ui.screens.login

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.runtime.getValue
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tasktodo.presentation.ui.widgets.ErrorField
import com.example.tasktodo.presentation.viewmodel.LoginViewModels
import com.example.tasktodo.presentation.ui.widgets.LoginEditFieldNullable
import com.example.tasktodo.presentation.ui.widgets.LoginLoadField
import com.example.tasktodo.presentation.viewmodel.MainViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun LoginStartScreen(loginStatus: MainViewModel){
    val loginViewModel: LoginViewModels = koinViewModel()
    val state by loginViewModel.uiState
    val color = Color.DarkGray

    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
        Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.width(screenWidth / 2)) {
            Box(Modifier.padding(3.dp)){
                Text("Вход", fontSize = 26.sp, color = Color.Black, fontWeight = FontWeight.ExtraBold, textAlign = TextAlign.Center)
            }
            Box(Modifier.padding(3.dp)){
                LoginEditFieldNullable(modifier = Modifier.fillMaxWidth().height(60.dp).border(BorderStroke(3.dp, if(state.isCorrectState in listOf(1, 3)) Color.Red else color),
                    RoundedCornerShape(10.dp)).clip(RoundedCornerShape(10.dp)),loginViewModel.tag, "Логин")
            }
            Box(Modifier.padding(3.dp)){
                LoginEditFieldNullable(modifier = Modifier.fillMaxWidth().height(60.dp).border(BorderStroke(3.dp, if(state.isCorrectState in listOf(2, 3)) Color.Red else color),
                    RoundedCornerShape(10.dp)).clip(RoundedCornerShape(10.dp)),loginViewModel.password, "Пароль")
            }
            Box(Modifier.padding(3.dp)){
                Button(modifier = Modifier.fillMaxWidth().height(40.dp).border(BorderStroke(3.dp, color),
                    RoundedCornerShape(10.dp)).clip(RoundedCornerShape(10.dp)), colors = ButtonColors(Color.Transparent, color, Color.LightGray, color), onClick = {loginViewModel.loadUser()}) {
                    Text("Войти", color = color)
                }
            }
            Box(Modifier.padding(3.dp)){
                Button(modifier = Modifier.fillMaxWidth().height(40.dp).border(BorderStroke(3.dp, color),
                    RoundedCornerShape(10.dp)).clip(RoundedCornerShape(10.dp)), colors = ButtonColors(Color.Transparent, color, Color.LightGray, color), onClick = {loginStatus.updateStatusLogin(false)}){
                    Text("Регистрация", color= color)
                }
            }
            Box(Modifier.padding(3.dp).height(60.dp)){
                if(state.errorLogin != null){
                    ErrorField(modifier = Modifier.fillMaxWidth().height(60.dp),state.errorLogin!!)
                }else if(state.isLoadLogin){
                    LoginLoadField("Попытка входа", modifier = Modifier.fillMaxWidth().height(60.dp))
                }
            }

        }
    }
}