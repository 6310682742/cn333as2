package com.example.quizgame.view

import com.example.quizgame.dataModel.Question

data class GameState (
    val gameState: Int = 0,
    val score: Int = 0,
    val count:Int = 0,
    val question: Question = Question()
)