package com.armandorochin.firebaseshowcaseapp.data.network

import com.armandorochin.firebaseshowcaseapp.data.response.UserResult
import com.armandorochin.firebaseshowcaseapp.ui.home.model.UserHome
import com.armandorochin.firebaseshowcaseapp.ui.signin.model.UserSignIn
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class UserService @Inject constructor(private val firebase: FirebaseClient) {

    companion object {
        const val USER_COLLECTION = "users"
    }

    suspend fun createUserTable(userSignIn: UserSignIn) = runCatching {

        val user = hashMapOf(
            "email" to userSignIn.email,
            "nickname" to userSignIn.nickName,
            "realname" to userSignIn.realName
        )

        firebase.db
            .collection(USER_COLLECTION)
            .document(firebase.auth.currentUser!!.uid)
            .set(user).await()

    }.isSuccess

    suspend fun getCurrentUserInfo(): UserResult = runCatching {
        firebase.db
            .collection(USER_COLLECTION)
            .document(firebase.auth.currentUser!!.uid)
            .get().await().toObject(UserResult.Data::class.java)
    }.toUserResult()

    private fun Result<UserResult.Data?>.toUserResult() = when (val result = getOrNull()) {
        null -> UserResult.Error
        else -> {
            UserResult.Data(true, result.email, result.nickname, result.realname)
        }
    }

}