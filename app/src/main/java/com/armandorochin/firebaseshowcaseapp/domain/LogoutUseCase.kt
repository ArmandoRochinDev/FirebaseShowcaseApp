package com.armandorochin.firebaseshowcaseapp.domain

import com.armandorochin.firebaseshowcaseapp.data.network.AuthService
import javax.inject.Inject

class LogoutUseCase @Inject constructor(private val authenticationService: AuthService){
    operator fun invoke() {
        authenticationService.logout()
    }
}