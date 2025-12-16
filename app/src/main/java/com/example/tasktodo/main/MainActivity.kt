package com.example.tasktodo.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.tasktodo.presentation.ui.screens.login.LoginStartScreen
import com.example.tasktodo.presentation.ui.screens.login.RegistrationScreen
import com.example.tasktodo.presentation.viewmodel.MainViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.example.tasktodo.ui.theme.TaskToDoTheme
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