package com.android.crypto.domain.useCase

import com.android.crypto.domain.entites.CoinsEntity
import com.android.crypto.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class GetCoinsUesCase @Inject constructor(private val coinRepository: CoinRepository) {

    suspend operator fun invoke(): Flow<Result<List<CoinsEntity>>> = flow {
        try {
            emit(coinRepository.getCoins())
        } catch (exception: HttpException) {
            emit(Result.failure(exception))
        } catch (exception: IOException) {
            emit(Result.failure(exception))
        }
    }

}