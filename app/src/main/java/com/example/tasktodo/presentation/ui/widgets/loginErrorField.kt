package com.example.tasktodo.presentation.ui.widgets

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ErrorField(modifier: Modifier, type: String){
    Column(modifier.fillMaxWidth().border(BorderStroke(3.dp, Color.Red), RoundedCornerShape(10.dp)).background(color = Color.Transparent, shape = RoundedCornerShape(10.dp)).clip(RoundedCornerShape(10.dp)).padding(10.dp), verticalArrangement = Arrangement.Center){
        Text("Ошибка:", Modifier.padding(bottom = 2.dp), color = Color.Red, fontSize = 12.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Start)
        Text(type,  color = Color.Red, fontSize = 16.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Start)
    }
}