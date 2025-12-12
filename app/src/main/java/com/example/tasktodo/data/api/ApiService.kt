package com.example.tasktodo.data.api
import com.example.tasktodo.data.api.dto.TaskDto
import com.example.tasktodo.data.api.dto.UserDto
import com.example.tasktodo.domain.entity.TaskEntity
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path


interface ApiService {
    @GET("users")
    suspend fun getUser(): List<UserDto>

    @GET("/task")
    suspend fun getTask(): List<TaskEntity>

    @GET("/task/{id}")
    suspend fun getTaskById(@Path("id") id: String): TaskEntity

    @POST("/task")
    suspend fun createTask(@Body task: TaskEntity): TaskEntity

    @PUT("/task/{id}")
    suspend fun updateTask(@Path("id") id: String, @Body task: TaskEntity): TaskEntity

    @DELETE("/task/{id}")
    suspend fun deleteTask(@Path("id") id: String)

}