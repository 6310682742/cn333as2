package com.example.quizgame.dataModel;

data class Question(
    val question: String="",
    val answerList: List<String> = listOf(),
    val correctAnswer:String = ""
    )
