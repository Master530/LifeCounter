package com.example.dicelife

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun ProfileScreen(){
    Box(modifier = Modifier
        .background(MaterialTheme.colors.secondary),
        contentAlignment = Alignment.Center,) {

        Image(
            painter = painterResource(id = R.drawable.waving_hand),
            contentDescription = "Dice",
            modifier = Modifier
                .size(128.dp)
                .clip(CircleShape)
                .border(2.dp, MaterialTheme.colors.secondary, CircleShape)
        )


        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.SpaceEvenly,) {
            Text(text = "Coming Soon!")
        }

    }
}