package com.example.tasktodo.data.servise

import android.util.Log
import com.example.tasktodo.domain.entity.UserEntity
import com.example.tasktodo.domain.repository.CustomDataValidator
import com.example.tasktodo.domain.repository.LoginRepository
import com.example.tasktodo.domain.repository.RegRepository
import com.example.tasktodo.domain.service.RegValidationService

class RegValidationServiceImpl(private val regRepository: RegRepository, private val validator: CustomDataValidator): RegValidationService {
    override suspend fun isEmailAvailable(email: String): Boolean {
        val validEmail = regRepository.gerUserEmail(email)
        return validEmail.find { validator.twoStringValidator(it, email) } != null
    }

    override suspend fun isTagAvailable(tag: String): Boolean {
        val usersTag = regRepository.getUserTag(tag)
        return usersTag.find { validator.twoStringValidator(tag, tag) } != null }
}