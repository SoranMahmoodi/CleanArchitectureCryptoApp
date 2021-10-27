package com.android.crypto.presntation.ui.fragment.coins

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.crypto.domain.entites.CoinsEntity
import com.android.crypto.domain.useCase.GetCoinsUesCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinsViewModel @Inject constructor(val getCoinsUesCase: GetCoinsUesCase) :ViewModel(){

    private var _coins = MutableSharedFlow<List<CoinsEntity>>()
    val coins = _coins.asSharedFlow()


    init {
        getCoins()
    }

    private fun getCoins()=viewModelScope.launch {
        getCoinsUesCase().onEach {
            it.onSuccess {
                _coins.emit(it)
            }
            it.onFailure {
                Log.i("CoinsViewModel", "getCoins: $it")
            }
        }.launchIn(viewModelScope)
    }
}