package com.estebanposada.simpleapp.presentation.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.estebanposada.data.usecase.GetCountryDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CountryDetailViewModel @Inject constructor(private val getCountryDetail: GetCountryDetail) :
    ViewModel() {
    private val _state: MutableStateFlow<CountryDetailState> =
        MutableStateFlow(CountryDetailState())

    val state: StateFlow<CountryDetailState>
        get() = _state.asStateFlow()

    fun event(event: CountryDetailEvent){
        when(event){
            CountryDetailEvent.LoadCountryData -> {
                viewModelScope.launch {

                }
            }
        }
    }
}