package com.estebanposada.simpleapp.presentation

import com.estebanposada.simpleapp.presentation.list.CountryListEvent
import com.estebanposada.simpleapp.presentation.list.CountryListSideEffect
import com.estebanposada.simpleapp.presentation.list.CountryListState
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

interface MVIContractEvent<EVENT>{
    fun event(event: EVENT)
}
interface MVIContract<STATE,EFFECT,EVENT>: MVIContractEvent<EVENT> {
    val state: StateFlow<STATE>
    val effect: SharedFlow<EFFECT>
}

interface CountryListContract: MVIContract<CountryListState, CountryListSideEffect, CountryListEvent>