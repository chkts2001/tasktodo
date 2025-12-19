package com.example.tasktodo.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.tasktodo.presentation.ui.screens.login.loginScreen.LoginStartScreen
import com.example.tasktodo.presentation.ui.screens.login.registrationScreen.RegistrationScreen
import com.example.tasktodo.presentation.viewmodel.MainViewModel
import androidx.compose.runtime.getValue
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val mainViewModel: MainViewModel = koinViewModel()
            val statusLogin by mainViewModel.isLogin
            if(statusLogin) LoginStartScreen(mainViewModel)
            else RegistrationScreen(mainViewModel)

        }
    }
}