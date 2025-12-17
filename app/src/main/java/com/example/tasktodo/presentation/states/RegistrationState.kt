package com.example.tasktodo.presentation.states

import androidx.compose.runtime.mutableStateOf
import com.example.tasktodo.domain.entity.UserEntity

data class RegistrationState(
    val currentUser: UserEntity? = null, // mutableStateOf<UserEntity?>(null)
    val isCorrect: Boolean = true, //mutableStateOf(true)
    val isLoadReg: Boolean = false, //mutableStateOf(false)
    val errorReg: String? = null //mutableStateOf<String?>(null)
)