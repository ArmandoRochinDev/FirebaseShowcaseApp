package com.armandorochin.firebaseshowcaseapp.data.response

sealed class ProfilePictureResult{
    object Error : ProfilePictureResult()
    data class Success(val imgUrl: String) : ProfilePictureResult()
}