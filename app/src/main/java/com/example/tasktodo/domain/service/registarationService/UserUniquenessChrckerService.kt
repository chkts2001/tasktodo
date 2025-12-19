package com.example.tasktodo.domain.service.registarationService

interface UserUniquenessCheckerService {
    suspend fun isEmailAvailable(email: String): Boolean
    suspend fun isTagAvailable(tag: String): Boolean
}