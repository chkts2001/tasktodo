package com.example.tasktodo.domain.error

sealed class LoginError: Throwable(){
    class MissingTag(): LoginError()
    class MissingPassword(): LoginError()
    class MissingBothParam: LoginError()
    class ErrorNotFound: LoginError()
    class NetworkError(cause: Throwable) : LoginError()
}