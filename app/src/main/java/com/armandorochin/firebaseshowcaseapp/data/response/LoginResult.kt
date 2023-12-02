package com.armandorochin.firebaseshowcaseapp.data.response

sealed class LoginResult {
    object Error : LoginResult()
    data class Success(val verified: Boolean) : LoginResult()
}