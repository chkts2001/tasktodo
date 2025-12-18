package com.example.tasktodo.presentation.viewmodel.registrationViewModel.mapper

import com.example.tasktodo.domain.error.RegError
import com.example.tasktodo.domain.error.RegErrorParam
import com.example.tasktodo.presentation.viewmodel.registrationViewModel.StateError

object RegErrorMapper{
    fun map(error: Throwable, tag: String, password: String, email: String): StateError = when(error){
        is RegError.MissingBothParam -> StateError("Поле с * незаполнено",tag.isBlank(), password.isBlank(), email.isBlank())
        is RegErrorParam.NameOccupied -> StateError("Имя @${error.message} уже занято", true, false, false)
        is RegError.EmailOccupied -> StateError("К данной почте уже привязан аккаунт", false, false, true)
        is RegError.NetworkError -> StateError(error.cause!!.message, false, false, false)
        else -> StateError(error!!.cause!!.message, false, false, false)
    }
}