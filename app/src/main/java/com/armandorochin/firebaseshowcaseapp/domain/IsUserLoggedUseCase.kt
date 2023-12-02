package com.armandorochin.firebaseshowcaseapp.domain

import com.armandorochin.firebaseshowcaseapp.data.network.AuthService
import com.armandorochin.firebaseshowcaseapp.data.response.LoginResult
import javax.inject.Inject

class IsUserLoggedUseCase @Inject constructor(private val authenticationService: AuthService){
    operator fun invoke(): Boolean {
        return authenticationService.isUserLogged()
    }
}