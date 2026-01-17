package com.example.safecity.ui.theme.ui.home




import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.example.safecity.ui.theme.Purple80


@Composable
fun ActionItem(title: String,icon : ImageVector,onClick: ()->Unit) {

    Card(
        modifier = Modifier.size(90.dp).clickable{onClick()},
        colors = CardDefaults.cardColors(Color(0xFF043615)),
        elevation = CardDefaults.cardElevation(6.dp),
    ) {
        Box(modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ){
                Icon(
                    imageVector = icon,
                    contentDescription = title,
                    tint = Purple80 ,
                    modifier = Modifier.size(35.dp)
                )
                Text(text = title, fontSize = 10.sp,color = Color.White)
            }
        }
    }
}
