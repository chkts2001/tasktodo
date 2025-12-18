package com.example.tasktodo.presentation.states

import androidx.compose.runtime.mutableStateOf
import com.example.tasktodo.domain.entity.UserEntity

data class LoginState(
    val user: UserEntity? = null,
    val tag: String = "",
    val password: String = "",
    val isLoadLogin: Boolean = false,
    val isCorrectState: Int? = null,
    val errorLogin: String? = null
//    val user = mutableStateOf<UserEntity?>(null)
//    val isLoadLogin = mutableStateOf(false)
//    val errorLogin = mutableStateOf<String?>(null)
)