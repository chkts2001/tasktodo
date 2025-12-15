package com.example.tasktodo.presentation.ui.screens.profile.head

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun profileFiled(profileName: String){
    Box()  {
        Row() {
            profileAvatar()
        }
    }
}

@Composable
fun profileAvatar(url: String){
    val contetx = LocalContext.current
    AsyncImage(
        model = ImageRequest.Builder(contetx)
            .data(url)
            .crossfade(true)
            .build(),
        contentDescription = "profile avatar",
        placeholder = null,
        error = null
    )
}