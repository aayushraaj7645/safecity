package com.example.safecity.ui.theme.ui.home

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

@Composable
fun SecondHeader(titleMain: String,titleSecondary :String ,safe : Boolean) {
    Text(
        text = titleMain, fontSize = 40.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center,color = if (safe){
            Color.White
        }
        else {
            Color.Red
        }
    )
    Text(
        text = titleSecondary, fontSize = 20.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center ,color = if (safe){
            Color.White
        }
        else {
            Color.Red
        }
    )

}