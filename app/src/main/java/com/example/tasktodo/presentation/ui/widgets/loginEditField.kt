package com.example.tasktodo.presentation.ui.widgets

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LoginEditField(modifier: Modifier, str: MutableState<String>, typeField: String) {
    Box(Modifier.padding(vertical = 3.dp)) {
        TextField(value = str.value, {str.value = it}, modifier = modifier, label = {
            Text(typeField, textAlign = TextAlign.Start, modifier = Modifier.padding(horizontal = 3.dp))
        })
    }
}

@Composable
fun ErrorField(modifier: Modifier, type: String){
    Box(modifier.fillMaxWidth().border(BorderStroke(3.dp, Color.Red), RoundedCornerShape(10.dp)).background(color = Color.Transparent, shape = RoundedCornerShape(10.dp)).clip(RoundedCornerShape(10.dp)).padding(10.dp)){
        Text(getTypeError(type), color = Color.Red, fontSize = 14.sp, textAlign = TextAlign.Start)
    }
}

fun getTypeError(type: String): String{
    return when(type){
        "HTTP 404 " -> "Неверный логин или пароль"
        else -> {"Неизвестная ошибка. Смотрите лог DEBUG"}
    }
}
