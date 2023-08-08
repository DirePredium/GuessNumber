package dev.trafficsquad.guessnumber.data

import retrofit2.http.GET
import retrofit2.http.Query

interface RandomNumberApiService {

    @GET("integers/?num=1&col=1&base=10&format=plain&rnd=new")
    suspend fun getRandomIntNumberAsync(
        @Query("min") min: Int,
        @Query("max") max: Int
    ): Int

}