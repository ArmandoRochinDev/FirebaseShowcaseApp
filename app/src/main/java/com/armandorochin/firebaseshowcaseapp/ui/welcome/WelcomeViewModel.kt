package com.armandorochin.firebaseshowcaseapp.ui.welcome

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.armandorochin.firebaseshowcaseapp.core.Event
import com.armandorochin.firebaseshowcaseapp.domain.IsUserLoggedUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WelcomeViewModel @Inject constructor(val isUserLoggedUseCase: IsUserLoggedUseCase): ViewModel(){

    private val _navigateToLogin = MutableLiveData<Event<Boolean>>()
    val navigateToLogin: LiveData<Event<Boolean>>
        get() = _navigateToLogin

    private val _navigateToSignIn = MutableLiveData<Event<Boolean>>()
    val navigateToSignIn: LiveData<Event<Boolean>>
        get() = _navigateToSignIn

    private val _navigateToHome = MutableLiveData<Event<Boolean>>()
    val navigateToHome: LiveData<Event<Boolean>>
        get() = _navigateToHome

    fun verifyUser(){
        if(isUserLoggedUseCase()){
            isUserLogged()
        }
    }

    private fun isUserLogged(){
        _navigateToHome.value = Event(true)
    }

    fun onLoginSelected() {
        _navigateToLogin.value = Event(true)
    }

    fun onSignInSelected() {
        _navigateToSignIn.value = Event(true)
    }

}