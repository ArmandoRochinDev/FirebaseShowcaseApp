package com.armandorochin.firebaseshowcaseapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.armandorochin.firebaseshowcaseapp.core.Event
import com.armandorochin.firebaseshowcaseapp.domain.LogoutUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(val logoutUseCase: LogoutUseCase): ViewModel(){

    private val _navigateToWelcome = MutableLiveData<Event<Boolean>>()
    val navigateToWelcome: LiveData<Event<Boolean>>
        get() = _navigateToWelcome

    fun onLogoutSelected(){
        logoutUseCase()
        _navigateToWelcome.value = Event(true)
    }
}