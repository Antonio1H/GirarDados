package com.example.projetodados

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.projetodados.ui.theme.ProjetoDadosTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProjetoDadosTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    DiceGameScreen()
                }
            }
        }
    }
}

@Composable
fun DiceGameScreen() {
    var dice1 by remember { mutableStateOf(1) }
    var dice2 by remember { mutableStateOf(1) }
    var totalRolls by remember { mutableStateOf(0) }
    var totalWins by remember { mutableStateOf(0) }
    var gameResult by remember { mutableStateOf("") }

    fun rollDice() {
        dice1 = (1..6).random()
        dice2 = (1..6).random()
        totalRolls++

        val sum = dice1 + dice2
        if (sum == 7 || sum == 11) {
            totalWins++
            gameResult = "Ganhou!"
        } else {
            gameResult = "Perdeu!"
        }
    }

    val winPercentage = if (totalRolls > 0) (totalWins.toFloat() / totalRolls * 100).toInt() else 0

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(onClick = { rollDice() }) {
            Text(text = "Jogar")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Resultado: $gameResult")
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Vitórias: $totalWins/$totalRolls = $winPercentage%")
    }
}
@Composable
fun DiceImages(dice1: Int, dice2: Int) {
    Row(
        modifier = Modifier.padding(16.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = getDiceImage(dice1)),
            contentDescription = "Dado 1",
            modifier = Modifier.size(100.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Image(
            painter = painterResource(id = getDiceImage(dice2)),
            contentDescription = "Dado 2",
            modifier = Modifier.size(100.dp)
        )
    }
}

fun getDiceImage(diceValue: Int): Int {
    return when (diceValue) {
        1 -> R.drawable.dado1
        2 -> R.drawable.dado2
        3 -> R.drawable.dado3
        4 -> R.drawable.dado4
        5 -> R.drawable.dado5
        6 -> R.drawable.dado6
        else -> R.drawable.dado1
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ProjetoDadosTheme {
        DiceGameScreen()
    }
}
