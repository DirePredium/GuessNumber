package dev.trafficsquad.guessnumber.data

import android.util.Log
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Executable
import kotlin.random.Random

    class RandomNumberApi : RandomNumberProvider {
        private val service: RandomNumberApiService

        init {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://www.random.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            service = retrofit.create(RandomNumberApiService::class.java)
        }

        override suspend fun getRandomIntNumber(from: Int, until: Int): Int? {
            try {
                return service.getRandomIntNumberAsync(from, until)
            } catch (e: Exception) {
                throw Exception("An error occurred while trying to connect to the API: ${e.message}", e)
            }
        }
    }