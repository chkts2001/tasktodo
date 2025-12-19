package com.example.tasktodo.presentation.ui.screens.registrationScreen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tasktodo.presentation.ui.screens.registrationScreen.components.GridErrors
import com.example.tasktodo.presentation.ui.utils.componentSizeUtils
import com.example.tasktodo.presentation.ui.components.LoginEditField
import com.example.tasktodo.presentation.ui.components.LoginLoadField
import com.example.tasktodo.presentation.viewmodel.MainViewModel
import com.example.tasktodo.presentation.viewmodel.registrationViewModel.RegAccountViewModels
import org.koin.androidx.compose.koinViewModel

@Composable()
fun RegistrationScreen(loginState: MainViewModel){

    val regViewModel: RegAccountViewModels = koinViewModel()
    val regState = regViewModel.uiState
    val color = Color.DarkGray

    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.width(componentSizeUtils())
        ) {
            Box(Modifier.padding(3.dp)) {
                Text(
                    "Регистрация",
                    fontSize = 26.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.ExtraBold,
                    textAlign = TextAlign.Center
                )
            }
            Box(Modifier.padding(3.dp)) {
                LoginEditField(
                    modifier = Modifier.fillMaxWidth().height(60.dp).border(
                        BorderStroke(3.dp, if(regState.isTagError) Color.Red else color),
                        RoundedCornerShape(10.dp)
                    ).clip(RoundedCornerShape(10.dp)), regState.tag, regViewModel::onTagChange, "Логин*"
                )
            }
            Box(Modifier.padding(3.dp)) {
                LoginEditField(
                    modifier = Modifier.fillMaxWidth().height(60.dp).border(
                        BorderStroke(3.dp, if(regState.isPasswordError) Color.Red else color),
                        RoundedCornerShape(10.dp)
                    ).clip(RoundedCornerShape(10.dp)), regState.password, regViewModel::onPasswordChange,"Пароль*"
                )
            }
            Box(Modifier.padding(3.dp)) {
                LoginEditField(
                    modifier = Modifier.fillMaxWidth().height(60.dp).border(
                        BorderStroke(3.dp, if(regState.isEmailError) Color.Red else color),
                        RoundedCornerShape(10.dp)
                    ).clip(RoundedCornerShape(10.dp)), regState.email, regViewModel::onEmailChange, "Почта*"
                )
            }
            Box(Modifier.padding(3.dp)) {
                LoginEditField(
                    modifier = Modifier.fillMaxWidth().height(60.dp).border(
                        BorderStroke(3.dp, color),
                        RoundedCornerShape(10.dp)
                    ).clip(RoundedCornerShape(10.dp)), regState.name, regViewModel::onNameChange,"Имя"
                )
            }
            Box(Modifier.padding(3.dp)) {
                LoginEditField(
                    modifier = Modifier.fillMaxWidth().height(60.dp).border(
                        BorderStroke(3.dp, color),
                        RoundedCornerShape(10.dp)
                    ).clip(RoundedCornerShape(10.dp)), regState.surname, regViewModel::onSurnameChange, "Фамилия"
                )
            }
            Box(Modifier.padding(3.dp)) {
                LoginEditField(
                    modifier = Modifier.fillMaxWidth().height(60.dp).border(
                        BorderStroke(3.dp, color),
                        RoundedCornerShape(10.dp)
                    ).clip(RoundedCornerShape(10.dp)), regState.birthday, regViewModel::onBirthDateChange, "Дата рождения"
                )
            }
            Row() {
                Box(Modifier.padding(3.dp).weight(1.5f)) {
                    Button(
                        modifier = Modifier.fillMaxWidth().height(40.dp).border(
                            BorderStroke(3.dp, color),
                            RoundedCornerShape(10.dp)
                        ).clip(RoundedCornerShape(10.dp)),
                        colors = ButtonColors(Color.Transparent, color, Color.LightGray, color),
                        onClick = {loginState.updateStatusLogin(true) }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "назад",
                            tint = Color.DarkGray,
                            modifier = Modifier.padding(0.dp)
                        )
                    }
                }
                Box(Modifier.padding(3.dp).weight(8.0f)) {
                    Button(
                        modifier = Modifier.fillMaxWidth().height(40.dp).border(
                            BorderStroke(3.dp, color),
                            RoundedCornerShape(10.dp)
                        ).clip(RoundedCornerShape(10.dp)),
                        colors = ButtonColors(Color.Transparent, color, Color.LightGray, color),
                        onClick = { regViewModel.regUser() }) {
                        Text("Зарегистрировать", color = color)
                    }
                }

            }
            Box(Modifier.padding(3.dp).height(60.dp)){
                if(regState.errorReg.isNotEmpty()){
                    GridErrors(regState.errorReg)
                    //ErrorField(modifier = Modifier.fillMaxWidth().height(60.dp), regState.errorReg)
                }else if(regState.isLoadReg){
                    LoginLoadField("Попытка регистрации", modifier = Modifier.fillMaxWidth().height(60.dp))
                }
            }
        }
    }

}