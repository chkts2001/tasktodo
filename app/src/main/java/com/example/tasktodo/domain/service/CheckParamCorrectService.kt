package com.example.tasktodo.domain.service

interface CheckParamCorrectService {
    suspend fun checkInclusionChars(value: String, chars: List<String>, isMandatory: Boolean): Boolean
    suspend fun checkCompleteRules(value: String, rules: List<Regex>, isMandatory: Boolean): Boolean
}