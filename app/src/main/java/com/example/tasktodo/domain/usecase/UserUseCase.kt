package com.example.tasktodo.domain.usecase

import com.example.tasktodo.domain.entity.UserEntity
import com.example.tasktodo.domain.repository.GetUserRepository
import com.example.tasktodo.domain.repository.SetUserRepository

class GetUserUseCase(private val userRepository: GetUserRepository) {
    suspend operator fun invoke(user: String): UserEntity{
        return userRepository.getUserInfo(user)
    }
}
class SetUserUseCase(private val setUserRepository: SetUserRepository){
    suspend operator fun invoke(user: UserEntity): UserEntity{
        return setUserRepository.createUser(user)
    }
}