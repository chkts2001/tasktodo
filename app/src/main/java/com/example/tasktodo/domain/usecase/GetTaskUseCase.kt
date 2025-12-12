package com.example.tasktodo.domain.usecase

import com.example.tasktodo.domain.entity.TaskEntity
import com.example.tasktodo.domain.repository.TaskRepository

class GetTaskUseCase(private val repository: TaskRepository) {
    suspend operator fun invoke(): List<TaskEntity>{
        return repository.getTask()
    }
}