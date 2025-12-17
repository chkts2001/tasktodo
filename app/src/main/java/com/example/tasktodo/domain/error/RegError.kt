package com.example.tasktodo.domain.error

sealed class RegError: Throwable() {
    class MissingBothParam: LoginError()
    class NetworkError(cause: Throwable) : LoginError()
    class NameOccupied(name: String): LoginError()
}