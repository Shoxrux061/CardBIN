package com.shoxrux.cardbin.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shoxrux.cardbin.core.ResultWrapper
import com.shoxrux.cardbin.data.model.CardBinResponse
import com.shoxrux.cardbin.domain.repository.BinRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BinViewModel @Inject constructor(private val repository: BinRepository) : ViewModel() {


    private val successData: MutableLiveData<CardBinResponse?> =
        MutableLiveData<CardBinResponse?>()
    val success: LiveData<CardBinResponse?>
        get() = successData

    private val errorData: MutableLiveData<String?> = MutableLiveData<String?>()
    val error: LiveData<String?>
        get() = errorData


    fun getCardBin(bin: String) {
        viewModelScope.launch {
            when (val result = repository.getCardBin(bin)) {
                is ResultWrapper.Success -> {
                    successData.postValue(result.data)
                }

                is ResultWrapper.Error -> {
                    errorData.postValue(result.code.toString())
                }

                is ResultWrapper.NetworkError -> {
                    errorData.postValue("Network Error")
                }
            }
        }
    }
}