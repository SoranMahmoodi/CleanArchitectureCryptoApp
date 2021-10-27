package com.android.crypto.presntation.ui.fragment.coin

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.crypto.domain.entites.CoinEntity
import com.android.crypto.domain.useCase.GetCoinUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinViewModel @Inject constructor(val getCoinUseCase: GetCoinUseCase): ViewModel(){

    private var _coin = MutableSharedFlow<CoinEntity>()
    val coin = _coin.asSharedFlow()

    fun getCoin(coinId:String)= viewModelScope.launch {
        getCoinUseCase(coinId).onEach {
            it.onSuccess {
                _coin.emit(it)
            }
            it.onFailure {
                Log.i("CoinViewModel", "getCoin: $it")
            }

        }.launchIn(viewModelScope)
    }
}