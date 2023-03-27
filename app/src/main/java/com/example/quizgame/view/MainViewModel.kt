package com.example.quizgame.view

import com.example.quizgame.data.QuestionList
import com.example.quizgame.dataModel.Question
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


class MainViewModel {
    private val _gameState  = MutableStateFlow(GameState())
    val gameState = _gameState.asStateFlow()
    private var used:MutableSet<Question> = mutableSetOf()
    private lateinit var question: Question
    init {
        _gameState.value = GameState()
    }
    fun checkAnswer(ans:String){
        _gameState.update {
            it.copy(
                count = _gameState.value.count + 1
            )
        }
        if(ans == question.correctAnswer) {
            _gameState.update {
                it.copy(
                    score = _gameState.value.score + 1
                )
            }
        }
    }
    fun checkGameState() {
        if(used.size == 10) {
            _gameState.update {
                update ->
                update.copy(
                    gameState=2
                )
            }
        }
    }
    fun startGame() {
//        used.clear()
        _gameState.update {
            update ->
            update.copy(
                gameState=1
            )
        }
        getNewQuestion()
    }
    private fun getNewQuestion() {
        var newQuestion:Question = QuestionList().questionList.filterNot { it in used }.random()
        _gameState.update {
            update ->
            update.copy(
                question = newQuestion,
                count = gameState.value.count + 1
            )
        }
        used.add(newQuestion)
        question = newQuestion
    }
}