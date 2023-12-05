package com.armandorochin.firebaseshowcaseapp.domain

import android.net.Uri
import com.armandorochin.firebaseshowcaseapp.data.network.StorageService
import com.armandorochin.firebaseshowcaseapp.data.response.UserResult
import javax.inject.Inject

class UploadProfilePictureUseCase @Inject constructor(private val storageService: StorageService) {
    suspend operator fun invoke(file:String,path: Uri):Boolean {
        return storageService.uploadProfilePicture(file, path)
    }
}