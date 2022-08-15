package com.sanket.androidplayground2.widgets.internet_observer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
open class InternetObserverViewModel @Inject constructor(
    private val connectivityObserver: ConnectivityObserver
) : ViewModel() {

    private val _internetLd = MutableLiveData<ConnectivityObserver.Status>()
    val internetLd: LiveData<ConnectivityObserver.Status> = _internetLd

    init {
        connectivityObserver.observe().onEach {
            _internetLd.value = it
        }.launchIn(viewModelScope)
    }

}