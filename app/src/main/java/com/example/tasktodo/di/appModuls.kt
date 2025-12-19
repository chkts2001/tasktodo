package com.example.tasktodo.di

import org.koin.androidx.viewmodel.dsl.viewModel
import com.example.tasktodo.data.api.ApiService
import com.example.tasktodo.data.repository.CustomDataValidatorImpl
import com.example.tasktodo.data.repository.LoginRepositoryImpl
import com.example.tasktodo.data.repository.RegRepositoryImpl
import com.example.tasktodo.data.repository.UserRepositoryImpl
import com.example.tasktodo.data.repository.TaskReadRepositoryImpl
import com.example.tasktodo.data.repository.TaskWriteRepositoryImpl
import com.example.tasktodo.data.servise.CheckParamCorrectServiceImpl
import com.example.tasktodo.data.servise.LoginValidationServiceImpl
import com.example.tasktodo.data.servise.registrationServiceImpl.RegValidationServiceImpl
import com.example.tasktodo.data.servise.registrationServiceImpl.UserUniquenessCheckerServiceImpl
import com.example.tasktodo.domain.repository.CustomDataValidator
import com.example.tasktodo.domain.repository.LoginRepository
import com.example.tasktodo.domain.repository.RegRepository
import com.example.tasktodo.domain.repository.UserRepository
import com.example.tasktodo.domain.repository.TaskReadRepository
import com.example.tasktodo.domain.repository.TaskWriteRepository
import com.example.tasktodo.domain.service.CheckParamCorrectService
import com.example.tasktodo.domain.service.LoginValidationService
import com.example.tasktodo.domain.service.registarationService.RegValidationService
import com.example.tasktodo.domain.service.registarationService.UserUniquenessCheckerService
import com.example.tasktodo.domain.usecase.LoginAccountUseCase
import com.example.tasktodo.domain.usecase.RegistrationAccountUseCase
import com.example.tasktodo.domain.usecase.TaskReadUseCase
import com.example.tasktodo.domain.usecase.TaskWriteUseCase
import com.example.tasktodo.presentation.viewmodel.loginViewModel.LoginViewModels
import com.example.tasktodo.presentation.viewmodel.MainViewModel
import com.example.tasktodo.presentation.viewmodel.TaskReadViewModel
import com.example.tasktodo.presentation.viewmodel.TaskWriteViewModel
import com.example.tasktodo.presentation.viewmodel.registrationViewModel.RegAccountViewModels
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
    single<UserRepository> { UserRepositoryImpl(get())}
    single<LoginRepository> { LoginRepositoryImpl(get()) }
    single<RegRepository> { RegRepositoryImpl(get()) }
    single<CustomDataValidator> { CustomDataValidatorImpl() }
    single<CheckParamCorrectService> { CheckParamCorrectServiceImpl() }
    single<RegValidationService> { RegValidationServiceImpl(get(), get(), get())}
    single<UserUniquenessCheckerService> { UserUniquenessCheckerServiceImpl(get(), get(),)}
    single<LoginValidationService> { LoginValidationServiceImpl(get(), get()) }

}

val domainModule = module{
    factory{ TaskReadUseCase(get<TaskReadRepository>()) }
    factory { TaskWriteUseCase(get<TaskWriteRepository>()) }
    factory { LoginAccountUseCase(get<CustomDataValidator>(), get<LoginValidationService>()) }
    factory { RegistrationAccountUseCase(get<RegRepository>(),  get<RegValidationService>()) }
}

val presentationModule = module{
    viewModel { MainViewModel() }
    viewModel { TaskReadViewModel(get<TaskReadUseCase>()) }
    viewModel { TaskWriteViewModel(get<TaskWriteUseCase>())}
    viewModel { LoginViewModels(get<LoginAccountUseCase>())}
    viewModel { RegAccountViewModels(get<RegistrationAccountUseCase>()) }
}

val appModules = listOf(dataModule, domainModule, presentationModule)
