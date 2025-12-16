package com.example.tasktodo.di

import org.koin.androidx.viewmodel.dsl.viewModel
import com.example.tasktodo.data.api.ApiService
import com.example.tasktodo.data.repository.UserRepositoryImpl
import com.example.tasktodo.data.repository.TaskReadRepositoryImpl
import com.example.tasktodo.data.repository.TaskWriteRepositoryImpl
import com.example.tasktodo.domain.repository.UserRepository
import com.example.tasktodo.domain.repository.TaskReadRepository
import com.example.tasktodo.domain.repository.TaskWriteRepository
import com.example.tasktodo.domain.usecase.GetUserProfileUseCase
import com.example.tasktodo.domain.usecase.CreateUserUseCase
import com.example.tasktodo.domain.usecase.GetUserTagUseCase
import com.example.tasktodo.domain.usecase.TaskReadUseCase
import com.example.tasktodo.domain.usecase.TaskWriteUseCase
import com.example.tasktodo.presentation.viewmodel.GetUserViewModels
import com.example.tasktodo.presentation.viewmodel.MainViewModel
import com.example.tasktodo.presentation.viewmodel.SetUserViewModels
import com.example.tasktodo.presentation.viewmodel.TaskReadViewModel
import com.example.tasktodo.presentation.viewmodel.TaskWriteViewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val MOCK_URL = "https://693bef54b762a4f15c3ed221.mockapi.io/"

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
    single<UserRepository> { UserRepositoryImpl(get()) }

}

val domainModule = module{
    factory{ TaskReadUseCase(get<TaskReadRepository>()) }
    factory { TaskWriteUseCase(get<TaskWriteRepository>()) }
    factory { GetUserProfileUseCase(get<UserRepository>()) }
    factory { GetUserTagUseCase(get<UserRepository>())}
    factory { CreateUserUseCase(get<UserRepository>()) }
}

val presentationModule = module{
    viewModel { MainViewModel() }
    viewModel { TaskReadViewModel(get<TaskReadUseCase>()) }
    viewModel { TaskWriteViewModel(get<TaskWriteUseCase>())}
    viewModel { GetUserViewModels(get<GetUserProfileUseCase>())}
    viewModel { SetUserViewModels(get<CreateUserUseCase>(), get<GetUserTagUseCase>()) }
}

val appModules = listOf(dataModule, domainModule, presentationModule)
