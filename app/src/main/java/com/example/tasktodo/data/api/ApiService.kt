package com.example.tasktodo.data.api
import com.example.tasktodo.data.api.dto.TaskDto
import com.example.tasktodo.data.api.dto.UserDto
import com.example.tasktodo.domain.entity.TaskEntity
import com.example.tasktodo.domain.entity.UserEntity
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path


interface ApiService {
    @GET("/user/{id}")
    suspend fun getUserInfo(@Path(value = "id") id: String): UserDto

    @POST("/user")
    suspend fun createUser(@Body user: UserEntity): UserDto

    @PUT("/user/{id}")
    suspend fun updateUserInfo(@Path(value = "id") id: String, @Body user: UserEntity): UserDto

    @DELETE("/user/{id}")
    suspend fun deleteUser(@Path(value = "id") id: String)


    @GET("/task")
    suspend fun getTask(): List<TaskDto>
    @GET("/task/{id}")
    suspend fun getTaskById(@Path("id") id: String): TaskDto
    @POST("/task")
    suspend fun createTask(@Body task: TaskEntity): TaskDto
    @PUT("/task/{id}")
    suspend fun updateTask(@Path("id") id: String, @Body task: TaskEntity): TaskDto
    @DELETE("/task/{id}")
    suspend fun deleteTask(@Path("id") id: String)

}