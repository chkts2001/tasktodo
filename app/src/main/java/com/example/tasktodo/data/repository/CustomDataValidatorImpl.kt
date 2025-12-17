package com.example.tasktodo.data.repository

import com.example.tasktodo.domain.error.LoginError
import com.example.tasktodo.domain.repository.CustomDataValidator

class CustomDataValidatorImpl(): CustomDataValidator {
    override fun twoStringValidator(firstStr: String?, secondStr: String?) = firstStr == secondStr
    override fun loginParamValidator(tag: String?, password: String?): Result<Unit> {
        return when{
            tag.isNullOrBlank() && password.isNullOrBlank() -> Result.failure(LoginError.MissingBothParam())
            tag.isNullOrBlank() -> Result.failure(LoginError.MissingTag())
            password.isNullOrBlank() -> Result.failure(LoginError.MissingPassword())
            else -> Result.success(Unit)

        }
    }

    override fun regParamValidator(listParam: List<String?>): Result<Unit> {
        for(param in listParam){
            if(param.isNullOrBlank()) return Result.failure(LoginError.MissingBothParam())
        }
        return Result.success(Unit)
    }
}