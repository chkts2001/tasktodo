package com.example.tasktodo.presentation.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import retrofit2.HttpException
import com.example.tasktodo.domain.entity.UserEntity
import com.example.tasktodo.domain.usecase.GetUserProfileUseCase
import com.example.tasktodo.domain.usecase.CreateUserUseCase
import com.example.tasktodo.domain.usecase.GetUserTagUseCase
import kotlinx.coroutines.launch


class GetUserViewModels(private val getUserUseCase: GetUserProfileUseCase): ViewModel(){
    val user = mutableStateOf<UserEntity?>(null)
    val tag = mutableStateOf("")
    val password = mutableStateOf("")
    val isLoadLogin = mutableStateOf(false)
    val errorLogin = mutableStateOf<String?>(null)

//    fun logTagUpdate(str: String){
//        tag.value = str
//    }
//
//    fun logPasswordUpdate(str: String){
//        password.value = str
//    }

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
            }catch (e: HttpException) {
                when (e.code()) {
                    404 -> {
                        errorLogin.value = "Неверный логин или пароль"
                    }
                }
            }catch(e: Exception){
                Log.d("debug", "Ошибка: ${e.message}")
                errorLogin.value = e.message
            }finally {
                isLoadLogin.value = false
            }
        }
    }
}
class SetUserViewModels(private val createUserUseCase: CreateUserUseCase, private val getUserTagUseCase: GetUserTagUseCase): ViewModel(){
    val currentUser = mutableStateOf<UserEntity?>(null)
    val tag = mutableStateOf("")
    val password = mutableStateOf("")
    val email = mutableStateOf("")
    //val avatar = mutableStateOf("")

    val isCorrect = mutableStateOf(true)
    val isLoadReg = mutableStateOf(false)
    val errorReg = mutableStateOf<String?>(null)

    fun regUser(){
        viewModelScope.launch {
            errorReg.value = null
            try {
                isLoadReg.value = true
                val checkTag = getUserTagUseCase(tag.value)
                for (user in checkTag) {
                    if (tag.value == user.tag) throw IllegalArgumentException("Имя @${tag.value} уже занято")
                }
            }catch (e: HttpException){
                when(e.code()){
                    404 -> {
                        val user = UserEntity(tag.value, email.value, password.value, "")
                        val responseCreate = createUserUseCase(user)
                        currentUser.value = responseCreate
                        if (currentUser.value == null) {
                            throw IllegalArgumentException("Ошибка регистрации. Попробуйте еще раз")
                        }
                    }
                }
            }catch(e: Exception){
                Log.d("debug", "Ошибка: ${e.message}")
                errorReg.value = e.message
            }finally {
                isLoadReg.value = false
            }
        }


    }

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


}