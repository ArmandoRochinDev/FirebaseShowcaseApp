package com.armandorochin.firebaseshowcaseapp.domain

import android.net.Uri
import com.armandorochin.firebaseshowcaseapp.data.network.StorageService
import com.armandorochin.firebaseshowcaseapp.data.response.ProfilePictureResult
import javax.inject.Inject

class GetProfilePictureUseCase @Inject constructor(private val storageService: StorageService) {
    suspend operator fun invoke():ProfilePictureResult = storageService.getUserPicture()

}