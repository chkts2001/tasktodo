package com.example.tasktodo.data.repository

import com.example.tasktodo.data.api.ApiService
import com.example.tasktodo.data.api.mapper.toEntity
import com.example.tasktodo.domain.entity.TaskEntity
import com.example.tasktodo.domain.repository.TaskRepository

class TaskRepositoryImpl(private val apiService: ApiService): TaskRepository {
    override suspend fun getTask(): List<TaskEntity>{
        return apiService.getTask().map { it.toEntity() }
    }
}