package com.example.tasktodo.presentation.ui.screens.settingProfileScreen

import android.util.Log
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tasktodo.presentation.ui.utils.componentSizeUtils
import com.example.tasktodo.presentation.ui.components.LoginEditField
import com.example.tasktodo.presentation.ui.components.UnderlineButton
import com.example.tasktodo.presentation.viewmodel.MainViewModel
import com.example.tasktodo.presentation.viewmodel.loginViewModel.LoginViewModels
import org.koin.androidx.compose.koinViewModel
import kotlin.collections.contains

@Composable
fun SettingsProfileScreen(loginState: MainViewModel) {
    val loginViewModel: LoginViewModels = koinViewModel()
    val loginState = loginViewModel.uiState
    val color = Color.DarkGray

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.width(componentSizeUtils())
        ) {
            Box(Modifier.padding(3.dp)) {
                Text(
                    "Настройки профиля",
                    fontSize = 26.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.ExtraBold,
                    textAlign = TextAlign.Center
                )
            }
            Box(Modifier.padding(3.dp)) {
                LoginEditField(
                    modifier = Modifier.fillMaxWidth().height(60.dp).border(
                        BorderStroke(
                            3.dp,
                            if (loginState.isCorrectState in listOf(1, 3)) Color.Red else color
                        ),
                        RoundedCornerShape(10.dp)
                    ).clip(RoundedCornerShape(10.dp)),
                    loginState.tag,
                    loginViewModel::onTagChange,
                    "Имя:"
                )
            }
            Box(Modifier.padding(3.dp)) {
                LoginEditField(
                    modifier = Modifier.fillMaxWidth().height(60.dp).border(
                        BorderStroke(
                            3.dp,
                            if (loginState.isCorrectState in listOf(2, 3)) Color.Red else color
                        ),
                        RoundedCornerShape(10.dp)
                    ).clip(RoundedCornerShape(10.dp)),
                    loginState.password,
                    loginViewModel::onPasswordChange,
                    "Фамилия:"
                )
            }
            Box(Modifier.padding(3.dp)) {
                LoginEditField(
                    modifier = Modifier.fillMaxWidth().height(60.dp).border(
                        BorderStroke(
                            3.dp,
                            if (loginState.isCorrectState in listOf(1, 3)) Color.Red else color
                        ),
                        RoundedCornerShape(10.dp)
                    ).clip(RoundedCornerShape(10.dp)),
                    loginState.tag,
                    loginViewModel::onTagChange,
                    "Дата рождения"
                )
            }
            Box(Modifier.padding(3.dp)) {
                Button(
                    modifier = Modifier.fillMaxWidth().height(40.dp).border(
                        BorderStroke(3.dp, color),
                        RoundedCornerShape(10.dp)
                    ).clip(RoundedCornerShape(10.dp)),
                    colors = ButtonColors(Color.Transparent, color, Color.LightGray, color),
                    onClick = { loginViewModel.loadUser() }) {
                    Text("Сохранить", color = color)
                }
            }
            Box(Modifier.padding(3.dp)) {
                UnderlineButton(Modifier.height(40.dp)) { Log.d("debug", "пропустить is click") }
            }
        }
    }
}
