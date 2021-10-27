package com.android.crypto.data.source

import com.android.crypto.data.repository.network.ApiService
import com.android.crypto.domain.entites.CoinEntity
import com.android.crypto.domain.entites.CoinsEntity
import com.android.crypto.domain.source.CoinSource
import javax.inject.Inject


class CoinSourceImpl @Inject constructor(private val apiService: ApiService) : CoinSource {

    override suspend fun getCoins(): Result<List<CoinsEntity>> {
        return try {
            val response = apiService.getCoins()
            if (response.isSuccessful) {
                response.body()?.let {
                    return@let Result.success(it)
                } ?: Result.failure(IllegalArgumentException(response.errorBody().toString()))
            } else {
                Result.failure(IllegalArgumentException(response.message()))
            }
        } catch (e: Exception) {
            Result.failure(IllegalArgumentException(e))
        }
    }

    override suspend fun getCoin(coinId: String): Result<CoinEntity> {
        return try {
            val response = apiService.getCoin(coinId)
            if (response.isSuccessful) {
                response.body()?.let {
                    return@let Result.success(it)
                } ?: Result.failure(IllegalArgumentException(response.errorBody().toString()))
            } else {
                Result.failure(IllegalArgumentException(response.message()))
            }
        } catch (e: Exception) {
            Result.failure(IllegalArgumentException(e))
        }

    }
}