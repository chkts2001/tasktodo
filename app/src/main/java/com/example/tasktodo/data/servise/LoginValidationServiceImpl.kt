package com.example.tasktodo.data.servise

import com.example.tasktodo.domain.entity.UserEntity
import com.example.tasktodo.domain.repository.CustomDataValidator
import com.example.tasktodo.domain.repository.LoginRepository
import com.example.tasktodo.domain.service.LoginValidationService

class LoginValidationServiceImpl(private val loginRepository: LoginRepository, private val validator: CustomDataValidator): LoginValidationService {

    override suspend fun isAccountAvailable(tag: String, password: String): UserEntity? {
        val users = loginRepository.getUserProfile(tag, password)
        return users.find { validator.twoStringValidator(it.tag, tag) && validator.twoStringValidator(it.password, password)}
    }
}