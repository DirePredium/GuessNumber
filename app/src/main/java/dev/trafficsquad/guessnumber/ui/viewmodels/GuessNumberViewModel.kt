package dev.trafficsquad.guessnumber.ui.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import dev.trafficsquad.guessnumber.data.GuessNumberModel
import dev.trafficsquad.guessnumber.data.GuessResult
import dev.trafficsquad.guessnumber.data.RandomNumberProvider
import kotlinx.coroutines.runBlocking

class GuessNumberViewModel(private val randomNumberProvider: RandomNumberProvider) : ViewModel() {

    private val model: GuessNumberModel = GuessNumberModel(randomNumberProvider)

    private var targetNumber: Int = 0
    private var attempts: Int = 5

    init {
        runBlocking {
            targetNumber = randomNumberProvider.getRandomIntNumber(1, 100) ?: 0
            Log.d("MyDebugLog", "Random int: $targetNumber")
        }
    }

    fun getAttemptsRemaining(): Int {
        return attempts
    }

    fun checkGuess(userGuess: Int): GuessResult {
        attempts--
        val result = model.checkGuess(userGuess, targetNumber)
        return result
    }

    fun areAnyAttempts() : Boolean {
        return attempts > 0
    }

    fun getAttempts() : Int {
        return this.attempts
    }
}