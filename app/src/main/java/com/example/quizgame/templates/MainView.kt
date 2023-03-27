package com.example.quizgame.templates

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quizgame.view.GameState
import com.example.quizgame.view.MainViewModel

@Composable
fun QuizGame(mainViewModel: MainViewModel = MainViewModel()) {
    val gameState by mainViewModel.gameState.collectAsState()
    Column {
//        PrintGameState(gameState = gameState)
        Header()
        GameCounter(gameState)
        GameBody(gameState = gameState, mainViewModel = mainViewModel)
    }
}
@Composable
fun PrintGameState(gameState: GameState) {
    Text(text = gameState.gameState.toString())
    Text(text = gameState.score.toString())
    Text(text = gameState.count.toString())
    Text(text = gameState.question.toString())
}
@Composable
fun Header() {
    Text(text = "Quiz Game", fontSize = 20.sp, modifier = Modifier.background(Color.Green).fillMaxWidth().padding(6.dp))
}
@Composable
fun GameBody(gameState: GameState, mainViewModel: MainViewModel) {
    if(gameState.gameState == 0) {
        StartGameButton(mainViewModel)
    }
    else if(gameState.gameState == 1){
        QuestionBody(gameState)
        AnswerBody(gameState,mainViewModel)

    }
    else {
        Result(gameState)
        StartGameButton(mainViewModel)
    }

}
@Composable
fun StartGameButton(mainViewModel: MainViewModel) {
    Button(onClick = {
        mainViewModel.startGame()
    }) {
        Text(text = "Start Game")
    }
}
@Composable
fun Result(gameState: GameState) {

}
@Composable
fun QuestionBody(gameState: GameState) {
    if(gameState.gameState != 1)return
    Text(text = gameState.question.question)
}
@Composable
fun AnswerBody(gameState: GameState, mainViewModel: MainViewModel) {
    if(gameState.gameState != 1)return

    for( ans in gameState.question.answerList) {
        Cardx(ans, mainViewModel)
    }
}
@Composable
fun InitBody() {

}
@Composable
fun GameCounter(gameState: GameState) {
    if(gameState.gameState == 0) return
    Text(text ="Quiz: " +  gameState.count.toString() + "/10")
    Text(text = String.format("Score: %s", gameState.score.toString()))
}
@Composable
fun Cardx(str:String, mainViewModel: MainViewModel) {
    Card(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .fillMaxWidth(),
        elevation = 2.dp,
        backgroundColor = Color.White,
        shape = RoundedCornerShape(corner = CornerSize(16.dp))
    ) {
        Row {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    , verticalArrangement = Arrangement.Center
            ) {
                Button(onClick = {
                    mainViewModel.checkAnswer(str)
                }) {
                    Text(text = str, style = typography.h6)
                }

            }
        }
    }
}