package com.example.tasktodo.presentation.ui.widgets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.time.format.TextStyle

@Composable
fun LoginLoadField(type: String, modifier: Modifier){
    Box(){
        Row(modifier = modifier, horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically){
            Text(type, fontSize = 16.sp, color = Color.Black, fontWeight = FontWeight.ExtraBold, textAlign = TextAlign.End, modifier = Modifier.padding(end = 3.dp).weight(2.0f))
            Column(modifier.weight(1.0f).padding(start = 3.dp), verticalArrangement = Arrangement.Center) {
                CircularProgressIndicator(Modifier.size(25.dp), color = Color.Black)
            }
        }
    }
}