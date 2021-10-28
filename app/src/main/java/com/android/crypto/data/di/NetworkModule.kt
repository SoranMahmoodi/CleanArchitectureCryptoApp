package com.android.crypto.data.di

import com.android.crypto.data.repository.network.ApiService
import com.android.crypto.data.repository.network.CoinRepositoryImpl
import com.android.crypto.data.utils.Constants
import com.android.crypto.domain.repository.CoinRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {


    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService =
        retrofit.create(ApiService::class.java)

    @Provides
    fun provideCoinRepository(coinRepositoryImpl: CoinRepositoryImpl): CoinRepository =
        coinRepositoryImpl


}