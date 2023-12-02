package com.armandorochin.firebaseshowcaseapp.data.network

import com.armandorochin.firebaseshowcaseapp.data.response.LoginResult
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser
import hilt_aggregated_deps._com_armandorochin_firebaseshowcaseapp_App_GeneratedInjector
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthService @Inject constructor(private val firebase: FirebaseClient){

    suspend fun login(email: String, password: String): LoginResult = runCatching {
        firebase.auth.signInWithEmailAndPassword(email, password).await()
    }.toLoginResult()

    suspend fun createAccount(email: String, password: String): AuthResult? {
        return firebase.auth.createUserWithEmailAndPassword(email, password).await()
    }

    fun isUserLogged(): Boolean {
        return firebase.auth.currentUser != null
    }

    fun getCurrentUser(): FirebaseUser {
        return firebase.auth.currentUser!!
    }

    private fun Result<AuthResult>.toLoginResult() = when (val result = getOrNull()) {
        null -> LoginResult.Error
        else -> {
            val userId = result.user
            checkNotNull(userId)
            LoginResult.Success(result.user?.isEmailVerified ?: false)
        }
    }
}