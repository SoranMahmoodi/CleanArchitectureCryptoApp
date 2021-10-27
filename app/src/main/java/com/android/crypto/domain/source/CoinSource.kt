package com.android.crypto.domain.source

import com.android.crypto.domain.entites.CoinEntity
import com.android.crypto.domain.entites.CoinsEntity

interface CoinSource {

    suspend fun getCoins():Result<List<CoinsEntity>>

    suspend fun getCoin(coinId:String): Result<CoinEntity>
}