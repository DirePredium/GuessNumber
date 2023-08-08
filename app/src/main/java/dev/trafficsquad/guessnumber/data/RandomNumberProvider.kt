package dev.trafficsquad.guessnumber.data

interface RandomNumberProvider {
    suspend fun getRandomIntNumber(from: Int, until: Int): Int?
}