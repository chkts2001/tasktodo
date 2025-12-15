package com.example.tasktodo.presentation.ui.widgets

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun LoginEditField(type: String, str: String): String {
    var result = ""
    Box(Modifier.padding(horizontal = 6.dp, vertical = 3.dp)) {
        TextField(value = str, {str = it}, label = {
            Text(type, textAlign = TextAlign.Start, modifier = Modifier.padding(horizontal = 3.dp))
        })
    }
}
