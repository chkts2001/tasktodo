package com.example.tasktodo.presentation.viewmodel.loginViewModel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tasktodo.domain.error.LoginError
import com.example.tasktodo.domain.usecase.LoginAccountUseCase
import com.example.tasktodo.presentation.states.LoginState
import kotlinx.coroutines.launch
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue


class LoginViewModels(private val loginUseCase: LoginAccountUseCase): ViewModel(){
    var uiState by mutableStateOf(LoginState())
        private set

    fun onTagChange(value: String){
        uiState = uiState.copy(tag = value)
    }
    fun onPasswordChange(value: String){
        uiState = uiState.copy(password = value)
    }

    fun loadUser(){
        viewModelScope.launch {
            uiState = uiState.copy(isLoadLogin = true, errorLogin = null, isCorrectState = null)
            Log.d("debug", "tag: ${uiState.tag}\npassword: ${uiState.password}")
            val result = loginUseCase(uiState.tag, uiState.password)
            uiState = result.fold(
                onSuccess = {currentUser ->
                    Log.d("debug", "login success:\nlogin (tag): ${currentUser.tag}\npassword: ${currentUser.password}")
                    uiState.copy(isLoadLogin = false, user = currentUser)
                },
                onFailure = {e ->
                    Log.d("debug", "Ошибка: ${e.message}")

                    val error = result.exceptionOrNull()
                    val(errorLogin, correctSate) = when(error){
                        is LoginError.MissingTag -> "Введите логин" to 1
                        is LoginError.MissingPassword -> "Введите пароль" to 2
                        is LoginError.MissingBothParam -> "Введите логин и пароль" to 3
                        is LoginError.ErrorNotFound -> "Неверный логин или пароль" to null
                        is LoginError.NetworkError -> "Ошибка сети: ${error.cause!!.message}" to null
                        else -> "Неизвестная ошибка. Смотрте лог DEBUG" to null
                    }
                    uiState.copy(isLoadLogin = false, isCorrectState = correctSate, errorLogin = errorLogin)
                }
            )
        }
    }
}

//    suspend fun createAccountApi(createUserUseCase: CreateUserUseCase){
//        val user = UserEntity(tag.value, email.value, password.value, "", listOf(name.value, surname.value, birthday.value))
//        val responseCreate = createUserUseCase(user)
//        currentUser.value = responseCreate
//        if (currentUser.value == null) {
//            throw IllegalArgumentException("Ошибка регистрации. Попробуйте еще раз")
//        }
//    }

//    fun regTagUpdate(str: String){
//        tag.value = str;
//    }
//
//    fun regPasswordUpdate(str: String) {
//        password.value = str
//    }
//
//    fun regEmailUpdate(str: String){
//        email.value = str
//    }
//    fun regAvatarUpdate(str: String){
//        avatar.value = str
//    }