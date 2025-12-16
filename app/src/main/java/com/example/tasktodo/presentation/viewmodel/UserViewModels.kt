package com.example.tasktodo.presentation.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tasktodo.domain.entity.UserEntity
import com.example.tasktodo.domain.usecase.GetUserUseCase
import com.example.tasktodo.domain.usecase.SetUserUseCase
import kotlinx.coroutines.launch


class GetUserViewModels(private val getUserUseCase: GetUserUseCase): ViewModel(){
    val user = mutableStateOf<UserEntity?>(null)
    val tag = mutableStateOf("")
    val password = mutableStateOf("")
    val isLoadLogin = mutableStateOf(false)
    val errorLogin = mutableStateOf<String?>(null)

    fun logTagUpdate(str: String){
        tag.value = str
    }

    fun logPasswordUpdate(str: String){
        password.value = str
    }

    fun loadUser(){
        viewModelScope.launch {
            isLoadLogin.value = true
            errorLogin.value = null
            try{
                Log.d("debug", "tag: ${tag.value}\npassword: ${password.value}")
                val response = getUserUseCase(tag.value, password.value)
                //Log.d("debug", response.toString())
                for(resp in response){
                    Log.d("debug", "ps_server: ${resp.password}\tps_user: ${password.value}")
                    if(resp.password == password.value) user.value = resp
                    else user.value = null
                }
                if(user.value == null) throw IllegalArgumentException("HTTP 404 ")
                Log.d("debug", "login success:\nlogin (tag): ${user.value!!.tag}\npassword: ${user.value!!.password}")
            }catch(e: Exception){
                Log.d("debug", "Ошибка: ${e.message}")
                errorLogin.value = e.message
            }finally {
                isLoadLogin.value = false
            }
        }
    }
}
class SetUserViewModels(private val setUserUseCase: SetUserUseCase): ViewModel(){
    val tag = mutableStateOf("")
    val password = mutableStateOf("")
    val email = mutableStateOf("")
    val avatar = mutableStateOf("")

    val isCorrect = mutableStateOf(true)
    val isLoadReg = mutableStateOf(false)
    val errorReg = mutableStateOf<String?>(null)

    fun regTagUpdate(str: String){
        tag.value = str;
    }

    fun regPasswordUpdate(str: String) {
        password.value = str
    }

    fun regEmailUpdate(str: String){
        email.value = str
    }
    fun regAvatarUpdate(str: String){
        avatar.value = str
    }


}