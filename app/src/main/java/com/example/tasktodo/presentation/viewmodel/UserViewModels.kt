package com.example.tasktodo.presentation.viewmodel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tasktodo.domain.entity.UserEntity
import com.example.tasktodo.domain.error.LoginError
import com.example.tasktodo.domain.error.RegError
import com.example.tasktodo.domain.usecase.LoginAccountUseCase
import com.example.tasktodo.domain.usecase.RegistrationAccountUseCase
import com.example.tasktodo.presentation.states.LoginState
import com.example.tasktodo.presentation.states.RegistrationState
import kotlinx.coroutines.launch


class LoginViewModels(private val loginUseCase: LoginAccountUseCase): ViewModel(){
    val tag: MutableState<String?> = mutableStateOf(null)
    val password: MutableState<String?> = mutableStateOf(null)
    val uiState = mutableStateOf(LoginState())


    fun loadUser(){
        viewModelScope.launch {
            uiState.value = uiState.value.copy(isLoadLogin = true, errorLogin = null, isCorrectState = null)
            Log.d("debug", "tag: ${tag}\npassword: $password")
            val result = loginUseCase(tag.value, password.value)
            uiState.value = result.fold(
                onSuccess = {currentUser ->
                    Log.d("debug", "login success:\nlogin (tag): ${currentUser.tag}\npassword: ${currentUser.password}")
                    uiState.value.copy(isLoadLogin = false, user = currentUser)
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
                    uiState.value.copy(isLoadLogin = false, isCorrectState = correctSate, errorLogin = errorLogin)
                }
            )
        }
    }
}
class RegAccountViewModels(private val regUseCase: RegistrationAccountUseCase): ViewModel() {
    val tag = mutableStateOf("")
    val password= mutableStateOf("")
    val email= mutableStateOf("")
    val name = mutableStateOf("")
    val surname = mutableStateOf("")
    val birthday = mutableStateOf("")

    val uiState = mutableStateOf(RegistrationState())

    //val avatar = mutableStateOf("")
    fun regUser() {
        viewModelScope.launch {
            uiState.value = uiState.value.copy(isLoadReg = true, errorReg = null, isCorrect = true)
            val formedEntity = UserEntity(
                tag.value,
                email.value,
                password.value,
                "",
                listOf(name.value, surname.value, birthday.value)
            )
            val result = regUseCase(formedEntity)
            uiState.value = result.fold(
                onSuccess = { currentUser ->
                    uiState.value.copy(isLoadReg = false, currentUser = currentUser, isCorrect = true)
                },
                onFailure = { e ->
                    val error = result.exceptionOrNull()
                    val(errorMes, isCorrect) = when(error){
                        is RegError.MissingBothParam -> "Поле с * незаполнено" to false
                        is RegError.NameOccupied -> "Имя @${error.cause!!.message} уже занято" to false
                        is RegError.NetworkError -> error.cause!!.message to false
                        else -> error!!.cause!!.message to false
                    }
                    uiState.value.copy(isLoadReg = false, errorReg = errorMes, isCorrect = isCorrect)
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