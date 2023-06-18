package com.example.dicelife

import android.text.style.BackgroundColorSpan
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.*
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import androidx.compose.foundation.layout.padding as padding1

//Notes:
// to fix the buttons being next to each other add space under the column and the remaining buttons
//and dice generators to make the design fall in place by force

//get help, have used the verticleArrangment to specify top & bottom part but screen the same(ish)


// add a seperate alert dialog box depending on which player lost , and show a winning message on the other side


@Composable
fun PlayPage() {
    val player1LifeState = remember { mutableStateOf(20) }
    val player2LifeState = remember { mutableStateOf(20) }
    val isGameLost = remember { mutableStateOf(false) }
    val diceResultDialog = remember { mutableStateOf(false) }
    var diceNumber by remember { mutableStateOf(0) }

    Column() {
        Box(
            modifier = Modifier
                .rotate(180f)
                .weight(1f)
                .fillMaxSize()
                .background(MaterialTheme.colors.primary)
                .background(Color.Black),
        ) {
            Row() {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxSize()
                        .background(MaterialTheme.colors.secondary),
                    contentAlignment = Alignment.Center,


                    ) {
                    Column(verticalArrangement = Arrangement.SpaceEvenly) {


                        Player2LifePoints(lifePoints = player2LifeState.value, Opponent = player1LifeState.value )


                        P2GainedLifeButton(onClick = { player2LifeState.value++ })


                        P2LostLifeButton(onClick = {
                            if (player2LifeState.value > 1) {
                                player2LifeState.value--
                            } else {
                                isGameLost.value = true
                            }
                        })
                    }
                }
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxSize()
                        .background(MaterialTheme.colors.secondary),
                    contentAlignment = Alignment.Center
                ) {
                    Column(Modifier.fillMaxSize()) {
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxSize()
                                .background(MaterialTheme.colors.secondary),
                            contentAlignment = Alignment.Center
                        ) {
                            Row() {  //make this be on the left of the life buttons
                                DiceGenerator("D-4") {
                                    diceNumber = (1..4).random()
                                    diceResultDialog.value = true
                                }

                                DiceGenerator("D-6") {
                                    diceNumber = (1..6).random()
                                    diceResultDialog.value = true
                                }

                                DiceGenerator("D-20") {
                                    diceNumber = (1..20).random()
                                    diceResultDialog.value = true
                                }
                            }
                        }

                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxSize()
                                .background(MaterialTheme.colors.secondary),
                            contentAlignment = Alignment.Center
                        ) {

                            Column(verticalArrangement = Arrangement.SpaceEvenly) {
                                Text(text = "Deck Selection coming here soon")
                                val currentTime = LocalTime.now()
                                val formatter = DateTimeFormatter.ofPattern("hh:mm a")
                                Text(text = formatter.format(currentTime))
                            }
                        }
                    }
                }
            }
        }
        
        Spacer(modifier = Modifier.height(2.dp))



        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxSize()
                .background(MaterialTheme.colors.primary),
            // contentAlignment = Alignment.Center
        ) {
            Row() {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxSize()
                        .background(MaterialTheme.colors.secondary),
                    contentAlignment = Alignment.Center,


                    ) {
                    Column(verticalArrangement = Arrangement.SpaceEvenly) {

                        Player1LifePoints(lifePoints = player1LifeState.value, Opponent =  player2LifeState.value)


                        P1GainedLifeButton(onClick = { player1LifeState.value++ })


                        P1LostLifeButton(onClick = {
                            if (player1LifeState.value > 1) {
                                player1LifeState.value--
                            } else {
                                isGameLost.value = true
                            }
                        })
                    }
                }
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxSize()
                        .background(MaterialTheme.colors.secondary),
                    contentAlignment = Alignment.Center
                ) {
                    Column(Modifier.fillMaxSize()) {
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxSize()
                                .background(MaterialTheme.colors.secondary),
                            contentAlignment = Alignment.Center
                        ) {
                            Row() {  //make this be on the left of the life buttons
                                DiceGenerator("D-4") {
                                    diceNumber = (1..4).random()
                                    diceResultDialog.value = true
                                }

                                DiceGenerator("D-6") {
                                    diceNumber = (1..6).random()
                                    diceResultDialog.value = true
                                }

                                DiceGenerator("D-20") {
                                    diceNumber = (1..20).random()
                                    diceResultDialog.value = true
                                }
                            }
                        }

                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxSize()
                                .background(MaterialTheme.colors.secondary),
                            contentAlignment = Alignment.Center
                        ) {

                            Column(verticalArrangement = Arrangement.SpaceEvenly) {
                                Text(text = "Deck Selection coming here soon")
                                val currentTime = LocalTime.now()
                                val formatter = DateTimeFormatter.ofPattern("hh:mm a")
                                Text(text = formatter.format(currentTime))
                            }
                        }
                    }
                }
            }
        }
    }


    if (diceResultDialog.value) {
        AlertDialog(
            onDismissRequest = { diceResultDialog.value = false },
            title = { Text(text = "Dice Results") },
            text = { Text(text = "You got a: $diceNumber") },
            confirmButton = {
                Button(onClick = { diceResultDialog.value = false }) {
                    Text(text = "OK")
                }
            }
        )
    }

    if (isGameLost.value) {
        AlertDialog(
            onDismissRequest = { isGameLost.value = false },
            title = { Text(text = "Game Over") },
            text = { Text(text = "Ruh Roh, You lost champ.") },
            confirmButton = {
                Button(onClick = { isGameLost.value = false }) {
                    Text(text = "OK")
                }
            }
        )
    }


}

@Composable
fun Player1LifePoints(lifePoints: Int, Opponent : Int) {
    Text(
        text = "Your Life: $lifePoints",
        style = MaterialTheme.typography.h6,
        color = MaterialTheme.colors.onPrimary,
    )
    Text(text = "Opponent Life: $Opponent",
    style = MaterialTheme.typography.h6,
    color = MaterialTheme.colors.onPrimary,)


}

@Composable
fun Player2LifePoints(lifePoints: Int, Opponent: Int) {
    Text(
        text = "Your Life: $lifePoints",
        style = MaterialTheme.typography.h6,
        color = MaterialTheme.colors.onPrimary,
    )
    Text(text = "Opponent Life: $Opponent",
        style = MaterialTheme.typography.h6,
        color = MaterialTheme.colors.onPrimary)
}

@Composable
fun P1GainedLifeButton(onClick: () -> Unit) {
    Button(onClick = onClick) {
        Text(text = "Life Gained!")
    }
}

@Composable
fun P1LostLifeButton(onClick: () -> Unit) {
    Button(onClick = onClick) {
        Text(text = "Life Lost?!?")
    }
}

@Composable
fun P2GainedLifeButton(onClick: () -> Unit) {
    Button(onClick = onClick) {
        Text(text = "Life Gained!")
    }
}

@Composable
fun P2LostLifeButton(onClick: () -> Unit) {
    Button(onClick = onClick) {
        Text(text = "Life Lost?!?")
    }
}

@Composable
fun DiceGenerator(label:String, onClick: () -> Unit) {
    Button(onClick = onClick) {
        Text(text = label)
    }
}



