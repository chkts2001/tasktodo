package com.example.tasktodo.domain.repository

interface CustomDataValidator{
    fun twoStringValidator(firstStr: String?, secondStr: String?): Boolean
}