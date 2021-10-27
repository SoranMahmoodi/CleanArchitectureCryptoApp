package com.android.crypto.data.repository.network

import com.android.crypto.data.source.CoinSourceImpl
import com.android.crypto.domain.entites.CoinEntity
import com.android.crypto.domain.entites.CoinsEntity
import com.android.crypto.domain.repository.CoinRepository
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(private val sourceImpl: CoinSourceImpl)
    :CoinRepository{
    override suspend fun getCoins(): Result<List<CoinsEntity>> {
        return sourceImpl.getCoins()
    }

    override suspend fun getCoin(coinId: String):Result<CoinEntity> {
        return sourceImpl.getCoin(coinId)
    }

}