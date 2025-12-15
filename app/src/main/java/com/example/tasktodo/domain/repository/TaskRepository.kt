package com.example.tasktodo.domain.repository

import com.example.tasktodo.domain.entity.TaskEntity

interface TaskReadRepository {
    suspend fun getTask(): List<TaskEntity>
    suspend fun getTaskByID(id: String): TaskEntity
}

interface TaskWriteRepository
{
    suspend fun createTask(task: TaskEntity): TaskEntity
    suspend fun updateTask(id: String, task: TaskEntity): TaskEntity
    suspend fun deleteTask(id: String)
}