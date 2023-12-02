package com.armandorochin.firebaseshowcaseapp.domain

import com.armandorochin.firebaseshowcaseapp.data.network.AuthService
import javax.inject.Inject

class IsUserLoggedUseCase @Inject constructor(private val authenticationService: AuthService){
    operator fun invoke(): Boolean = authenticationService.isUserLogged()
}