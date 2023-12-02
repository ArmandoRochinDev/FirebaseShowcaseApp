package com.armandorochin.firebaseshowcaseapp.domain

import com.armandorochin.firebaseshowcaseapp.data.network.AuthService
import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject

class GetCurrentUserUseCase @Inject constructor(private val authenticationService: AuthService) {
    operator fun invoke(): FirebaseUser {
        return authenticationService.getCurrentUser()
    }
}