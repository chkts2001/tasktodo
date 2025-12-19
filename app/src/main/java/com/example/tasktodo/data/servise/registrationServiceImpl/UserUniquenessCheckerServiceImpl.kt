package com.example.tasktodo.data.servise.registrationServiceImpl

import com.example.tasktodo.domain.repository.CustomDataValidator
import com.example.tasktodo.domain.repository.RegRepository
import com.example.tasktodo.domain.service.registarationService.UserUniquenessCheckerService

class UserUniquenessCheckerServiceImpl(private val repository: RegRepository, private val validator: CustomDataValidator):
    UserUniquenessCheckerService {
    override suspend fun isEmailAvailable(email: String): Boolean {
        val validEmail = repository.gerUserEmail(email)
        return validEmail.find { validator.twoStringValidator(it, email) } != null
    }

    override suspend fun isTagAvailable(tag: String): Boolean {
        val usersTag = repository.getUserTag(tag)
        return usersTag.find { validator.twoStringValidator(tag, tag) } != null }
}