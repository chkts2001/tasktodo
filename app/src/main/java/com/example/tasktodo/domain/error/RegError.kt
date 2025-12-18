package com.example.tasktodo.domain.error

sealed class RegError: Throwable() {
//    class MissingTagField: RegError()
//    class MissingPasswordField: RegError()
//    class MissingMailError: RegError()
    class MissingBothParam: RegError()
    class EmailOccupied: RegError()
    class NetworkError(cause: Throwable) : RegError()
}

sealed class RegErrorParam(message: String? = null): Exception(message){
    class NameOccupied(val name: String?): RegErrorParam(name)
}