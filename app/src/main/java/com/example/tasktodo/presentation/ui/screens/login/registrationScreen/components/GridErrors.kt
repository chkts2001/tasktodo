package com.example.tasktodo.presentation.ui.screens.login.registrationScreen.components

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.tasktodo.domain.error.RegError
import com.example.tasktodo.presentation.ui.widgets.ErrorField

@Composable
fun GridErrors(errorList: List<String?>) {
    LazyVerticalGrid(columns = GridCells.Fixed(1)) {
        items(errorList.size){ index ->
            ErrorField(modifier = Modifier.fillMaxWidth().height(60.dp), errorList[index])
        }
    }
}