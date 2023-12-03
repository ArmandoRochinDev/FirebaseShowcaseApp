package com.armandorochin.firebaseshowcaseapp.ui.login

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.armandorochin.firebaseshowcaseapp.core.Event
import com.armandorochin.firebaseshowcaseapp.data.response.LoginResult
import com.armandorochin.firebaseshowcaseapp.ui.login.model.UserLogin
import com.armandorochin.firebaseshowcaseapp.domain.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(val loginUseCase: LoginUseCase) : ViewModel() {

    private companion object {
        const val MIN_PASSWORD_LENGTH = 6
    }

    //renombnra estas variables bro
    private val _navigateToDetails = MutableLiveData<Event<Boolean>>()
    val navigateToDetails: LiveData<Event<Boolean>>
        get() = _navigateToDetails

    private val _navigateToForgotPassword = MutableLiveData<Event<Boolean>>()
    val navigateToForgotPassword: LiveData<Event<Boolean>>
        get() = _navigateToForgotPassword

    private val _navigateToSignIn = MutableLiveData<Event<Boolean>>()
    val navigateToSignIn: LiveData<Event<Boolean>>
        get() = _navigateToSignIn

    private val _viewState = MutableStateFlow(LoginViewState())
    val viewState: StateFlow<LoginViewState>
        get() = _viewState

    private var _showErrorDialog = MutableLiveData(UserLogin())
    val showErrorDialog: LiveData<UserLogin>
        get() = _showErrorDialog

    fun onLoginSelected(email: String, password: String) {
        if (isValidEmail(email) && isValidPassword(password)) {
            loginUser(email, password)
        } else {
            onFieldsChanged(email, password)
        }
    }

    private fun loginUser(email: String, password: String) {
        viewModelScope.launch {
            _viewState.value = LoginViewState(isLoading = true)
            when (loginUseCase(email, password)) {
                LoginResult.Error -> {
                    _showErrorDialog.value =
                        UserLogin(email = email, password = password, showErrorDialog = true)
                    _viewState.value = LoginViewState(isLoading = false)
                }
                is LoginResult.Success -> {
                    //TODO: cambiar nombre de esta var
                    _navigateToDetails.value = Event(true)
                }
            }
            _viewState.value = LoginViewState(isLoading = false)
        }
    }

    fun onFieldsChanged(email: String, password: String) {
        _viewState.value = LoginViewState(
            isValidEmail = isValidEmail(email),
            isValidPassword = isValidPassword(password)
        )
    }

    fun onSignInSelected() {
        _navigateToSignIn.value = Event(true)
    }

    private fun isValidEmail(email: String) =
        Patterns.EMAIL_ADDRESS.matcher(email).matches() || email.isEmpty()

    private fun isValidPassword(password: String): Boolean =
        password.length >= MIN_PASSWORD_LENGTH || password.isEmpty()

}
