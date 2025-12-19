package com.example.tasktodo.presentation.viewmodel.registrationViewModel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tasktodo.domain.entity.UserEntity
import com.example.tasktodo.domain.usecase.RegistrationAccountUseCase
import com.example.tasktodo.presentation.states.RegistrationState
import com.example.tasktodo.presentation.viewmodel.registrationViewModel.mapper.RegErrorMapper
import kotlinx.coroutines.launch
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

data class StateError(
    val message: List<String?>,
    val errorTag: Boolean,
    val errorPassword: Boolean,
    val errorEmail: Boolean
)
class RegAccountViewModels(private val regUseCase: RegistrationAccountUseCase): ViewModel() {
    var uiState by mutableStateOf(RegistrationState())
        private set

    fun onTagChange(value: String){
        uiState = uiState.copy(tag = value)
    }
    fun onPasswordChange(value: String){
        uiState = uiState.copy(password = value)
    }
    fun onEmailChange(value: String){
        uiState = uiState.copy(email = value)
    }
    fun onNameChange(value: String){
        uiState = uiState.copy(name = value)
    }
    fun onSurnameChange(value: String){
        uiState = uiState.copy(surname = value)
    }
    fun onBirthDateChange(value: String){
        uiState = uiState.copy(birthday = value)
    }

    //val avatar = mutableStateOf("")
    fun regUser() {
        viewModelScope.launch {
            uiState = uiState.copy(isLoadReg = true, errorReg = emptyList(), isTagError = false, isPasswordError = false, isEmailError = false)
            val formedEntity = UserEntity(uiState.tag, uiState.email, uiState.password, "", listOf(uiState.name, uiState.surname, uiState.birthday)
            )
            val result = regUseCase(formedEntity)
            uiState = result.fold(
                onSuccess = { currentUser ->
                    uiState.copy(isLoadReg = false, currentUser = currentUser, isCorrect = true)
                },
                onFailure = {
                    val stateError = RegErrorMapper.map(result.exceptionOrNull()!!, uiState.tag, uiState.password, uiState.email)
                    uiState.copy(isLoadReg = false, errorReg = stateError.message, isTagError = stateError.errorTag, isPasswordError = stateError.errorPassword, isEmailError = stateError.errorEmail)
                }
            )
        }
    }
}