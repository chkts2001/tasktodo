package com.example.tasktodo.presentation.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class MainViewModel(): ViewModel(){
    val isLogin = mutableStateOf(true)
    fun updateStatusLogin(status: Boolean){
        isLogin.value = status
    }
}