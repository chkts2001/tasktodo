package com.example.tasktodo.di

import org.koin.androidx.viewmodel.dsl.viewModel
import com.example.tasktodo.data.api.ApiService
import com.example.tasktodo.data.repository.GetUserRepositoryImpl
import com.example.tasktodo.data.repository.SetUserRepositoryImpl
import com.example.tasktodo.data.repository.TaskReadRepositoryImpl
import com.example.tasktodo.data.repository.TaskWriteRepositoryImpl
import com.example.tasktodo.domain.repository.GetUserRepository
import com.example.tasktodo.domain.repository.SetUserRepository
import com.example.tasktodo.domain.repository.TaskReadRepository
import com.example.tasktodo.domain.repository.TaskWriteRepository
import com.example.tasktodo.domain.usecase.GetUserUseCase
import com.example.tasktodo.domain.usecase.SetUserUseCase
import com.example.tasktodo.domain.usecase.TaskReadUseCase
import com.example.tasktodo.domain.usecase.TaskWriteUseCase
import com.example.tasktodo.presentation.viewmodel.TaskReadViewModel
import com.example.tasktodo.presentation.viewmodel.TaskWriteViewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val MOCK_URL = "https://mockapi.io/projects/693bef54b762a4f15c3ed222"

val dataModule = module {
    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl(MOCK_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    single<ApiService>{get<Retrofit>().create(ApiService::class.java) }
    single<TaskWriteRepository> { TaskWriteRepositoryImpl(get()) }
    single<TaskReadRepository> { TaskReadRepositoryImpl(get()) }
    single<SetUserRepository> { SetUserRepositoryImpl(get()) }
    single<GetUserRepository> { GetUserRepositoryImpl(get()) }

}

val domainModule = module{
    factory{ TaskReadUseCase(get<TaskReadRepository>()) }
    factory { TaskWriteUseCase(get<TaskWriteRepository>()) }
    factory { SetUserUseCase(get<SetUserRepository>()) }
    factory { GetUserUseCase(get<GetUserRepository>()) }
}

val presentationModule = module{
    viewModel { TaskReadViewModel(get<TaskReadUseCase>()) }
    viewModel { TaskWriteViewModel(get<TaskWriteUseCase>())}
}

val appModules = listOf(dataModule, domainModule, presentationModule)
