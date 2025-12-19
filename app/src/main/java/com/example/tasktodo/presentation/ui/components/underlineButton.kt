package com.example.tasktodo.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.material3.Button
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.sp

@Composable
fun UnderlineButton(modifier: Modifier, fu: () -> Unit){
    Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center){
        Button(modifier = modifier, colors = ButtonColors(Color.Transparent, Color.LightGray, Color.Transparent, Color.DarkGray), onClick= {fu}){
            Text("пропустить", textAlign = TextAlign.Center, fontSize = 14.sp, textDecoration = TextDecoration.Underline)
        }
    }
}