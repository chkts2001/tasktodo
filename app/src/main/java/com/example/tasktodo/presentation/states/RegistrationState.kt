package com.example.tasktodo.presentation.states

import androidx.compose.runtime.mutableStateOf
import com.example.tasktodo.domain.entity.UserEntity

data class RegistrationState(
    val currentUser: UserEntity? = null,
    val isCorrect: Boolean = true,
    val isLoadReg: Boolean = false,
    val errorReg: String? = null
)