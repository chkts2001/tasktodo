package com.example.tasktodo.presentation.ui.screens.login

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tasktodo.presentation.ui.widgets.ErrorField
import com.example.tasktodo.presentation.ui.widgets.LoginEditField
import com.example.tasktodo.presentation.viewmodel.MainViewModel
import com.example.tasktodo.presentation.viewmodel.SetUserViewModels
import org.koin.androidx.compose.koinViewModel

@Composable()
fun RegistrationScreen(loginState: MainViewModel){

    val regViewModel: SetUserViewModels = koinViewModel()
    val isCorrect by regViewModel.isCorrect
    val isLoadReg by regViewModel.isLoadReg
    val errorReg by regViewModel.errorReg
    val color = Color.DarkGray

    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.width(screenWidth / 2)
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
                        BorderStroke(3.dp, color),
                        RoundedCornerShape(10.dp)
                    ).clip(RoundedCornerShape(10.dp)), regViewModel.tag, "Логин"
                )
            }
            Box(Modifier.padding(3.dp)) {
                LoginEditField(
                    modifier = Modifier.fillMaxWidth().height(60.dp).border(
                        BorderStroke(3.dp, color),
                        RoundedCornerShape(10.dp)
                    ).clip(RoundedCornerShape(10.dp)), regViewModel.password, "Пароль"
                )
            }
            Box(Modifier.padding(3.dp)) {
                LoginEditField(
                    modifier = Modifier.fillMaxWidth().height(60.dp).border(
                        BorderStroke(3.dp, color),
                        RoundedCornerShape(10.dp)
                    ).clip(RoundedCornerShape(10.dp)), regViewModel.email, "Почта"
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

            if (errorReg != null) {
                Box(Modifier.padding(3.dp)) {
                    ErrorField(modifier = Modifier.fillMaxWidth().height(40.dp), errorReg!!)
                }
            }
        }
    }

}