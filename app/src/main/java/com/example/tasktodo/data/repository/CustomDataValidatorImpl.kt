package com.example.tasktodo.data.repository

import com.example.tasktodo.domain.repository.CustomDataValidator

class CustomDataValidatorImpl(): CustomDataValidator {
    override fun twoStringValidator(firstStr: String?, secondStr: String?) = firstStr == secondStr
}