package com.armandorochin.firebaseshowcaseapp.domain

import com.armandorochin.firebaseshowcaseapp.data.network.AuthService
import com.armandorochin.firebaseshowcaseapp.ui.signin.model.UserSignIn
import javax.inject.Inject

class CreateAccountUseCase @Inject constructor(
    private val authenticationService: AuthService
) {

    suspend operator fun invoke(userSignIn: UserSignIn): Boolean {
        return authenticationService.createAccount(userSignIn.email, userSignIn.password) != null
    }
}