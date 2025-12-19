package com.example.tasktodo.presentation.ui.utils

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@SuppressLint("ConfigurationScreenWidthHeight")
@Composable
fun componentSizeUtils(): Dp {
    val screenWidth = LocalConfiguration.current.screenWidthDp.toFloat()
    Log.d("debug", "${screenWidth}")

    val minScreen = 320f
    val maxScreen = 600f

    val minP = 0.3f
    val maxP = 0.8f

    val interpolation = ((screenWidth - minScreen) / (maxScreen - minScreen)).coerceIn(0f,1f)
    val fraction = maxP - (minP * interpolation)
    return screenWidth.dp * fraction

}