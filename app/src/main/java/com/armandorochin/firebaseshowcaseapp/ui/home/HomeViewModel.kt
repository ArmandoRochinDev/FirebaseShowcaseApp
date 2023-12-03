package com.armandorochin.firebaseshowcaseapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.armandorochin.firebaseshowcaseapp.core.Event
import com.armandorochin.firebaseshowcaseapp.data.response.UserResult
import com.armandorochin.firebaseshowcaseapp.domain.GetCurrentUserUseCase
import com.armandorochin.firebaseshowcaseapp.domain.LogoutUseCase
import com.armandorochin.firebaseshowcaseapp.ui.home.model.UserHome
import com.armandorochin.firebaseshowcaseapp.ui.login.LoginViewState
import com.armandorochin.firebaseshowcaseapp.ui.login.model.UserLogin
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(val logoutUseCase: LogoutUseCase, val getCurrentUserUseCase: GetCurrentUserUseCase): ViewModel(){

    private val _navigateToWelcome = MutableLiveData<Event<Boolean>>()
    val navigateToWelcome: LiveData<Event<Boolean>>
        get() = _navigateToWelcome

    private val _userInfo = MutableLiveData<UserHome>()
    val userInfo:LiveData<UserHome>
        get() = _userInfo

    private val _viewState = MutableStateFlow(HomeViewState())
    val viewState: StateFlow<HomeViewState>
        get() = _viewState

    private var _showErrorDialog = MutableLiveData(UserHome())
    val showErrorDialog: LiveData<UserHome>
        get() = _showErrorDialog

    fun onLogoutSelected(){
        logoutUseCase()
        _navigateToWelcome.value = Event(true)
    }

    fun getUserInfo(){
        viewModelScope.launch {
            _viewState.value = HomeViewState(isLoading = true, isValidUser = false)
            when(val result = getCurrentUserUseCase()){
                UserResult.Error -> {
                    _showErrorDialog.value =
                        UserHome(email = "", nickname = "", realname = "", showErrorDialog = true)
                        _viewState.value = HomeViewState(isLoading = false, isValidUser = false)
                }
                is UserResult.Data -> {
                    _userInfo.value = result.toUserHome()
                    _viewState.value = HomeViewState(isLoading = false, isValidUser = true)
                }
            }
        }
    }
}