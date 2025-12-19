package com.example.tasktodo.data.servise.registrationServiceImpl

import com.example.tasktodo.domain.entity.UserEntity
import com.example.tasktodo.domain.error.RegError
import com.example.tasktodo.domain.error.RegErrorParam
import com.example.tasktodo.domain.repository.CustomDataValidator
import com.example.tasktodo.domain.service.CheckParamCorrectService
import com.example.tasktodo.domain.service.registarationService.RegValidationService
import com.example.tasktodo.domain.service.registarationService.UserUniquenessCheckerService
import retrofit2.HttpException

class RegValidationServiceImpl(private val unqueness: UserUniquenessCheckerService, private val validator: CustomDataValidator, private val correctService: CheckParamCorrectService):
    RegValidationService {

    override suspend fun validateUser(user: UserEntity): Result<UserEntity> {
        validator.regParamValidator(listOf(user.tag, user.password, user.email)).onFailure{
            return Result.failure(RegError.MissingBothParam())
        }
        if(isEmailOccupied(user.email)) return Result.failure(RegError.EmailOccupied())
        isIncorrectError(correctService.checkInclusionChars(user.email, listOf("@", "."), true), correctService.checkCompleteRules(user.password, listOf("^.{8,}$".toRegex()), true)).getOrElse { return Result.failure(it) }
        if(isTagOccupied(user.tag)) return Result.failure(RegErrorParam.NameOccupied(user.tag))
        return Result.success(user)
    }

    private fun isIncorrectError(passwordAvail: Boolean, emailAvail: Boolean): Result<Boolean>{
        return if(passwordAvail){
            if (emailAvail) Result.success(true)
            else Result.failure(RegError.IncorrectPassword())
        }else{
            if(emailAvail) Result.failure(RegError.IncorrectEmail())
            else Result.failure(RegError.IncorrectInputParam())
        }
    }

    private suspend fun isEmailOccupied(email: String): Boolean =
        try{
            unqueness.isEmailAvailable(email)
        }catch(e: HttpException){
            if(e.code() == 404) false else throw e
        }

    private suspend fun isTagOccupied(tag: String): Boolean =
        try{
            unqueness.isTagAvailable(tag)
        }catch (e: HttpException){
            if(e.code() == 404) false else throw e
        }
}