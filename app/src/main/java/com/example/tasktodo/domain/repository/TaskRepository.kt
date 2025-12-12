package com.example.tasktodo.domain.repository

import com.example.tasktodo.domain.entity.TaskEntity

interface TaskReadRepository {
    suspend fun getTask(): List<TaskEntity>
}

interface TaskWriteRepository
{
    suspend fun createTask(task: TaskEntity): TaskEntity
    suspend fun updateTask(task: TaskEntity): TaskEntity
    suspend fun deleteTask(id: String)
}