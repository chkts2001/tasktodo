package com.example.tasktodo.data.servise

import com.example.tasktodo.domain.service.CheckParamCorrectService

class CheckParamCorrectServiceImpl(): CheckParamCorrectService{
    override suspend fun checkInclusionChars(value: String, chars: List<String>, isMandatory: Boolean): Boolean {
        return if(isMandatory) chars.all(value::contains) else chars.any(value::contains)
    }

    override suspend fun checkCompleteRules(value: String, rules: List<Regex>, isMandatory: Boolean): Boolean {
        return if(isMandatory) { rules.all{rule -> value.matches(rule)} } else rules.any{ rule -> value.matches(rule)}
    }
}
