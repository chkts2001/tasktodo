package com.example.tasktodo.presentation.ui.widgets

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.tasktodo.presentation.states.RegistrationState

@Composable
fun LoginEditFieldNullable(modifier: Modifier, str: MutableState<String?>, typeField: String) {
    Box(Modifier.padding(vertical = 3.dp)) {
        TextField(value = if(str.value == null) "" else str.value!!,
            {str.value = it}, modifier = modifier, label = {
            Text(typeField, textAlign = TextAlign.Start, modifier = Modifier.padding(horizontal = 3.dp))
        })
    }
}

@Composable
fun LoginEditField(modifier: Modifier, value: String, valueChange: (String) -> Unit, typeField: String) {
    Box(Modifier.padding(vertical = 3.dp)) {
        TextField(value = value, onValueChange = valueChange, modifier = modifier, label = {
            Text(typeField, textAlign = TextAlign.Start, modifier = Modifier.padding(horizontal = 3.dp))
        })
    }
}

//fun getTypeError(type: String): String{
//    return when(type){
//        "HTTP 404 " -> "Неверный логин или пароль"
//        else -> {"Неизвестная ошибка. Смотрите лог DEBUG"}
//    }
//}
