package com.example.tasktodo.presentation.states

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.tasktodo.domain.entity.UserEntity

data class RegistrationState(
    val currentUser: UserEntity? = null,
    val tag: String = "",
    val password: String = "",
    val email: String = "",
    val name: String = "",
    val surname: String = "",
    val birthday: String = "",
    val isCorrect: Boolean = true,
    val isLoadReg: Boolean = false,
    val isTagError: Boolean = false,
    val isPasswordError: Boolean = false,
    val isEmailError: Boolean = false,
    val errorReg: String? = null,
)