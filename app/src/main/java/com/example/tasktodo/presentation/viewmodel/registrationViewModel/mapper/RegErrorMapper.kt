package com.example.tasktodo.presentation.viewmodel.registrationViewModel.mapper

import com.example.tasktodo.domain.error.RegError
import com.example.tasktodo.domain.error.RegErrorParam
import com.example.tasktodo.presentation.viewmodel.registrationViewModel.StateError

object RegErrorMapper{
    fun map(error: Throwable, tag: String, password: String, email: String): StateError = when(error){
        is RegError.MissingBothParam -> StateError(listOf("Поле с * незаполнено"),tag.isBlank(), password.isBlank(), email.isBlank())
        is RegErrorParam.NameOccupied -> StateError(listOf("Имя @${error.message} уже занято"), true, false, false)
        is RegError.EmailOccupied -> StateError(listOf("К данной почте уже привязан аккаунт"), false, false, true)
        is RegError.IncorrectEmail -> StateError(listOf("Неккоректный адрес почты. Проверьте наличие \"*\" и \".\""), false, false, true)
        is RegError.IncorrectPassword-> StateError(listOf("Неккоректный пароль. Минимальная длина - 8"), false, true, false)
        is RegError.IncorrectInputParam -> StateError(listOf("Неккоректный адрес почты. Проверьте наличие \"*\" и \".\"", "Неккоректный пароль. Минимальная длина - 8"), false, true, true)
        is RegError.NetworkError -> StateError(listOf(error.cause!!.message), false, false, false)
        else -> StateError(listOf(error!!.cause!!.message), false, false, false)
    }
}