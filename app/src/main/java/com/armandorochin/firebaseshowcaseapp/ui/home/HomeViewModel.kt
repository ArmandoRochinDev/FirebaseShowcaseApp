package com.armandorochin.firebaseshowcaseapp.ui.home

import android.content.Context
import android.net.Uri
import androidx.core.content.FileProvider
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.armandorochin.firebaseshowcaseapp.core.Event
import com.armandorochin.firebaseshowcaseapp.core.createImageFile
import com.armandorochin.firebaseshowcaseapp.data.response.UserResult
import com.armandorochin.firebaseshowcaseapp.domain.GetCurrentUserUseCase
import com.armandorochin.firebaseshowcaseapp.domain.LogoutUseCase
import com.armandorochin.firebaseshowcaseapp.domain.UploadProfilePictureUseCase
import com.armandorochin.firebaseshowcaseapp.ui.home.model.UserHome
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.io.File
import java.util.Objects
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    val logoutUseCase: LogoutUseCase,
    val getCurrentUserUseCase: GetCurrentUserUseCase,
    val uploadProfilePictureUseCase: UploadProfilePictureUseCase
): ViewModel(){
    private var file: File? = null
    private var userEmail:String = ""
    private var capturedImageUri = Uri.EMPTY

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

    fun getUserInfo(context: Context){
        viewModelScope.launch {
            _viewState.value = HomeViewState(isLoading = true, isValidUser = false)
            when(val result = getCurrentUserUseCase()){
                UserResult.Error -> {
                    _showErrorDialog.value =
                        UserHome(email = "", nickname = "", realname = "", showErrorDialog = true)
                        _viewState.value = HomeViewState(isLoading = false, isValidUser = false)
                }
                is UserResult.Data -> {
                    initUri(context, result.email)
                    _userInfo.value = result.toUserHome()
                    _viewState.value = HomeViewState(isLoading = false, isValidUser = true)
                }
            }
        }
    }

    private fun initUri(context: Context, email:String) {
        file = context.createImageFile(email.replace(".","").replace("@",""))
        capturedImageUri =  FileProvider.getUriForFile(
            Objects.requireNonNull(context),
            context.packageName + ".provider",
            file!!
        )
    }

    fun getUri():Uri{
        return capturedImageUri
    }

    suspend fun uploadPicture(){
        uploadProfilePictureUseCase(file!!.name, capturedImageUri)
    }
}