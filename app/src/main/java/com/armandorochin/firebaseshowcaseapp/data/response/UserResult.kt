package com.armandorochin.firebaseshowcaseapp.data.response

import com.armandorochin.firebaseshowcaseapp.ui.home.model.UserHome

sealed class UserResult {
    object Error : UserResult()
    data class Data(
        val verified: Boolean = false,
        val email: String = "",
        val nickname: String = "",
        val realname: String = ""
    ) : UserResult(){
        fun toUserHome():UserHome{
            return UserHome(
                email, nickname, realname, false, null
            )
        }
    }
}