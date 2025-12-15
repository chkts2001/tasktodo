package com.example.tasktodo.data.repository

import androidx.compose.ui.res.stringResource
import com.example.tasktodo.data.api.ApiService
import com.example.tasktodo.data.api.mapper.toEntity
import com.example.tasktodo.domain.entity.TaskEntity
import com.example.tasktodo.domain.repository.TaskReadRepository
import com.example.tasktodo.domain.repository.TaskWriteRepository

class TaskReadRepositoryImpl(private val apiService: ApiService): TaskReadRepository {
    override suspend fun getTask(): List<TaskEntity> {
        return apiService.getTask().map { it.toEntity() }
    }

    override suspend fun getTaskByID(id: String): TaskEntity {
        return apiService.getTaskById(id).toEntity()
    }
}

class TaskWriteRepositoryImpl(private val apiService: ApiService): TaskWriteRepository{
    override suspend fun createTask(task: TaskEntity): TaskEntity{
        return apiService.createTask(task).toEntity()
    }
    override suspend fun updateTask(id: String, task: TaskEntity): TaskEntity{
        return apiService.updateTask(id, task).toEntity()
    }
    override suspend fun deleteTask(id: String){
        return apiService.deleteTask(id)
    }
}