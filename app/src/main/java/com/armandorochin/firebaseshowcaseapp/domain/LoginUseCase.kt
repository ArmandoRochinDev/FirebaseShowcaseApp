package com.armandorochin.firebaseshowcaseapp.domain

import com.armandorochin.firebaseshowcaseapp.data.network.AuthService
import com.armandorochin.firebaseshowcaseapp.data.response.LoginResult
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val authenticationService: AuthService) {

    suspend operator fun invoke(email: String, password: String): LoginResult =
        authenticationService.login(email, password)
}