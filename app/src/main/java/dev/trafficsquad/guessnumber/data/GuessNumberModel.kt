package dev.trafficsquad.guessnumber.data

class GuessNumberModel(private val randomNumberProvider: RandomNumberProvider) {
    suspend fun getRandomNumber(): Int {
        return randomNumberProvider.getRandomIntNumber(1, 100) ?: 0
    }

    fun checkGuess(userGuess: Int, targetNumber: Int): GuessResult {
        return when {
            userGuess < targetNumber -> GuessResult.TOO_LOW
            userGuess > targetNumber -> GuessResult.TOO_HIGH
            else -> GuessResult.CORRECT
        }
    }
}