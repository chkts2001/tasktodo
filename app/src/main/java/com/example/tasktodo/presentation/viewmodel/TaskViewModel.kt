package com.example.tasktodo.presentation.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tasktodo.domain.entity.TaskEntity
import com.example.tasktodo.domain.usecase.GetTaskUseCase
import kotlinx.coroutines.launch

class TaskViewModel(private val getTaskUseCase: GetTaskUseCase): ViewModel() {
    val task = mutableStateListOf<TaskEntity>()
    val isLoad = mutableStateOf(false)
    val error = mutableStateOf<String?>(null)

    fun fetchTasks(){
        viewModelScope.launch {
            isLoad.value = true
            error.value = null
            try{
                val response = getTaskUseCase()
                task.clear()
                task.addAll(response)
            }catch(e: Exception){
                error.value = "Ошибка: ${e.message}"
            }finally {
                isLoad.value = false
            }
        }
    }
}