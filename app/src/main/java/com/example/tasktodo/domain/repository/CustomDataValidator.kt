package com.example.tasktodo.domain.repository

interface CustomDataValidator{
    fun twoStringValidator(firstStr: String?, secondStr: String?): Boolean
    fun loginParamValidator(tag: String?, password: String?): Result<Unit>
    fun regParamValidator(listParam: List<String?>): Result<Unit>
}