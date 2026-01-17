package com.example.safecity.ui.theme.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Header(title: String,safe : Boolean) {
    Text(text = title, fontSize = 20.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center,   modifier = Modifier
        .fillMaxWidth()
        .background(
            color = if (safe) {
                Color.Black
            } else {
                Color.Red
            }
        )
        .padding(20.dp),color = if (safe){
        Color.White
    }
    else {
        Color.Black
    })
}

