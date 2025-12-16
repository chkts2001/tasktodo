package com.example.tasktodo.domain.usecase

import com.example.tasktodo.domain.entity.UserEntity
import com.example.tasktodo.domain.repository.UserRepository

class GetUserProfileUseCase(private val userRepository: UserRepository) {
    suspend operator fun invoke(user: String, password: String): List<UserEntity>{
        return userRepository.getUserProfile(user, password)
    }
}
class GetUserTagUseCase(private val userRepository: UserRepository) {
    suspend operator fun invoke(user: String): List<UserEntity>{
        return userRepository.getUserTag(user)
    }
}
class CreateUserUseCase(private val setUserRepository: UserRepository){
    suspend operator fun invoke(user: UserEntity): UserEntity{
        return setUserRepository.createUser(user)
    }
}