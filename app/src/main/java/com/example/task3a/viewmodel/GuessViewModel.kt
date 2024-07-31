package com.example.task3a.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GuessViewModel : ViewModel() {
    private val _randomNumber = MutableLiveData<Int>()
    val randomNumber: LiveData<Int> = _randomNumber

    private val _randomChar = MutableLiveData<Char>()
    val randomChar: LiveData<Char> = _randomChar

    private val _result = MutableLiveData<String>()
    val result: LiveData<String> = _result

    private val _guessedNumber = MutableLiveData<Int?>()
    val guessedNumber: LiveData<Int?> = _guessedNumber

    init {
        clearGame()
    }

    private fun generateNewNumber() {
        _randomNumber.value = (0..9).random()
    }

    private fun generateNewChar() {
        _randomChar.value = ('A'..'Z').random()
    }

    private fun clearGame() {
        generateNewNumber()
        generateNewChar()
        _result.value = "TEKRAR DENE"
        _guessedNumber.value = null
        _result.postValue(_result.value)
    }

    fun selectNumber(number: Int) {
        _guessedNumber.value = number
    }

    fun guess() {
        _guessedNumber.value?.let { guessedNumber ->
            _randomNumber.value?.let { randomNumber ->
                _result.value = if (guessedNumber == randomNumber) {
                    "KAZANDIN!"
                } else {
                    "TEKRAR DENE"
                }
            }
        }
    }

    fun clearGuess() {
        clearGame()
    }
}