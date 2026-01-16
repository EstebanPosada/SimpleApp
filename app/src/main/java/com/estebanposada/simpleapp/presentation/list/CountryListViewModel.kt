package com.estebanposada.simpleapp.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.estebanposada.data.usecase.GetCountryListUseCase
import com.estebanposada.simpleapp.presentation.CountryListContract
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CountryListViewModel @Inject constructor(
    private val getCountryListUseCase: GetCountryListUseCase,
) : ViewModel(), CountryListContract {
    private val _state: MutableStateFlow<CountryListState> =
        MutableStateFlow(CountryListState.Loading)
    private val _effect: MutableSharedFlow<CountryListSideEffect> = MutableSharedFlow()

    override val state: StateFlow<CountryListState>
        get() = _state.asStateFlow()
    override val effect: SharedFlow<CountryListSideEffect>
        get() = _effect.asSharedFlow()

    override fun event(event: CountryListEvent) {
        when (event) {
            is CountryListEvent.CountryClicked -> {
                viewModelScope.launch {
                    _effect.emit(CountryListSideEffect.NavigateToDetail(event.id))
                }
            }

            CountryListEvent.LoadCountryList -> {
                updateState(CountryListState.Loading)
                loadCountries()
            }
        }
    }

    private fun loadCountries() {
        viewModelScope.launch {
            val result = getCountryListUseCase()
            updateState(CountryListState.Success(countries = result))
            /*getCountryListUseCase().fold(
                { updateState(CountryListState.Error("")) },
                {updateState(CountryListState.Success(countries = emptyList()))}
            )*/
        }
    }

    private fun updateState(error: CountryListState) {
        _state.update { error }
    }

}