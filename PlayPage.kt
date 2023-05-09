package com.example.dicelife

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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
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

    Column(Modifier.height(300.dp)) {

        Row(
            modifier = Modifier
                .background(MaterialTheme.colors.primary)
                .padding1(16.dp)
                .rotate(180f),
        ) {
            // top half with flipped orientation

            Column() {
                //this rows modifier isn't working properly, find out why
                Row(modifier = Modifier.align(Alignment.Start)) {  //make this be on the left of the life buttons
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

            Column() {

                Spacer(modifier = Modifier.height(16.dp))
                Player2LifePoints(lifePoints = player2LifeState.value)
                Spacer(modifier = Modifier.height(16.dp))
                P2GainedLifeButton(onClick = { player2LifeState.value++ })
                Spacer(modifier = Modifier.height(8.dp))
                P2LostLifeButton(onClick = {
                    if (player2LifeState.value > 1) {
                        player2LifeState.value--
                    } else {
                        isGameLost.value = true
                    }
                })


//            if(isGameLost.value){
//                LostMessage()// Call the lost game message from here
//            }
                //remove post testing
                //}
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
        Column(
            Modifier
                .height(300.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Bottom
        ) {
            // Second half with rotated orientation
            Row {
                Column(
                    modifier = Modifier
                        .background(MaterialTheme.colors.primary)
                        .padding1(16.dp),
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

                    Spacer(modifier = Modifier.height(16.dp))
                    Player1LifePoints(lifePoints = player1LifeState.value)

                    Spacer(modifier = Modifier.height(16.dp))
                    P1GainedLifeButton(onClick = { player1LifeState.value++ })

                    Spacer(modifier = Modifier.height(8.dp))
                    P1LostLifeButton(onClick = {
                        if (player1LifeState.value > 1) {
                            player1LifeState.value--
                        } else {
                            isGameLost.value = true// Calling the lost game message from here
                        }
                    })
                    Spacer(modifier = Modifier.height(10.dp))

                }
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

    }


@Composable
fun Player1LifePoints(lifePoints: Int) {
    Text(
        text = "P1 Life Points: $lifePoints",
        style = MaterialTheme.typography.h6,
        color = MaterialTheme.colors.onPrimary,
    )
}

@Composable
fun Player2LifePoints(lifePoints: Int) {
    Text(
        text = "P2 Life Points: $lifePoints",
        style = MaterialTheme.typography.h6,
        color = MaterialTheme.colors.onPrimary,
    )
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



