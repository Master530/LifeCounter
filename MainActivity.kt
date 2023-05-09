package com.example.dicelife

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

//post it to github

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HomeScreen()
            
        }
    }
}

@Composable
fun HomeScreen() {
    val screenState = remember { mutableStateOf("home") }

    
    Scaffold(
        bottomBar = { BottomNavigation(screenState.value) { screenState.value = it } }
    ) { innerPadding -> /*eve*/
        when (screenState.value) {
            "home" -> HomeScreenContent()
            "play" -> PlayPage()
            "profile" -> ProfileScreen()
        }
    }
}

@Composable
fun BottomNavigation(
    currentScreen: String,
    onScreenChange: (String) -> Unit
) {
    BottomNavigation(
        modifier = Modifier
            .background(MaterialTheme.colors.surface)
    ) {
        BottomNavigationItem(
            selected = currentScreen == "home",
            onClick = { onScreenChange("home") },
            icon = {
                IconButton(
                    onClick = { onScreenChange("home") }
                ) {
                    Icon(
                        imageVector = Icons.Default.AccountCircle,
                        contentDescription = "account profile button"
                    )
                }
            },
            label = { Text(stringResource(R.string.bottom_navigation_home)) }
        )
        BottomNavigationItem(
            selected = currentScreen == "play",
            onClick = { onScreenChange("play") },
            icon = {
                IconButton(
                    onClick = { onScreenChange("play") }
                ) {
                    Icon(
                        imageVector = Icons.Default.PlayArrow,
                        contentDescription = "Play game button"
                    )
                }
            },
            label = { Text(stringResource(R.string.bottom_navigation_play)) }
        )

        BottomNavigationItem(
            selected = currentScreen == "profile",
            onClick = { onScreenChange("profile") },
            icon = {
                IconButton(
                    onClick = { onScreenChange("profile") }
                ) {
                    Icon(
                        imageVector = Icons.Default.Home,
                        contentDescription = "Home menu button"
                    )
                }
            },
            label = { Text(stringResource(R.string.bottom_navigation_profile)) }
        )
    }
}

@Composable
fun HomeScreenContent(){



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
