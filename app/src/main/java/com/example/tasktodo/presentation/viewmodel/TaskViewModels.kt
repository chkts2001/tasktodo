package com.example.tasktodo.presentation.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tasktodo.domain.entity.TaskEntity
import com.example.tasktodo.domain.usecase.TaskReadUseCase
import com.example.tasktodo.domain.usecase.TaskWriteUseCase
import kotlinx.coroutines.launch

class TaskReadViewModel(private val taskReadUseCase: TaskReadUseCase): ViewModel() {
    val task = mutableStateListOf<TaskEntity>()
    val isLoad = mutableStateOf(false)
    val error = mutableStateOf<String?>(null)

    fun fetchTasks(){
        viewModelScope.launch {
            isLoad.value = true
            error.value = null
            try{
                val response = taskReadUseCase()
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

class TaskWriteViewModel(private val taskWriteUseCase: TaskWriteUseCase): ViewModel(){

}