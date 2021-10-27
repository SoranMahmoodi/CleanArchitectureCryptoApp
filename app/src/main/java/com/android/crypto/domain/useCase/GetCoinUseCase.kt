package com.android.crypto.domain.useCase

import com.android.crypto.domain.entites.CoinEntity
import com.android.crypto.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class GetCoinUseCase @Inject constructor(private val coinRepository: CoinRepository) {

    suspend operator fun invoke(coinId: String): Flow<Result<CoinEntity>> = flow {
        try {
            emit(coinRepository.getCoin(coinId))
        } catch (exception: HttpException) {
            emit(Result.failure(exception))
        } catch (exception: IOException) {
            emit(Result.failure(exception))
        }
    }

}