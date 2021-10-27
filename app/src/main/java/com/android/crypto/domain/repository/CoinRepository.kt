package com.android.crypto.domain.repository

import com.android.crypto.domain.entites.CoinEntity
import com.android.crypto.domain.entites.CoinsEntity
import kotlinx.coroutines.flow.Flow

interface CoinRepository {

    suspend fun getCoins():Result<List<CoinsEntity>>

    suspend fun getCoin(coinId:String): Result<CoinEntity>
}