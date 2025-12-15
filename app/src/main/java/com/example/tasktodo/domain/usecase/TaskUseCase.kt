package com.example.tasktodo.domain.usecase

import com.example.tasktodo.domain.entity.TaskEntity
import com.example.tasktodo.domain.repository.TaskReadRepository
import com.example.tasktodo.domain.repository.TaskWriteRepository

class TaskReadUseCase(private val repository: TaskReadRepository) {
    suspend operator fun invoke(): List<TaskEntity>{
        return repository.getTask()
    }
}

class TaskWriteUseCase(private val repository: TaskWriteRepository){
    suspend operator fun invoke(task: TaskEntity): TaskEntity{
        return repository.createTask(task)
    }
}