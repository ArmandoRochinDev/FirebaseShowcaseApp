package com.armandorochin.firebaseshowcaseapp.ui.home.model

data class UserHome (
    val email: String = "",
    val nickname: String = "",
    val realname: String = "",
    val showErrorDialog: Boolean = true,
    var imgUrl: String? = null
)