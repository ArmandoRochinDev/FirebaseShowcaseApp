package com.armandorochin.firebaseshowcaseapp.data.network

import android.net.Uri
import com.armandorochin.firebaseshowcaseapp.data.response.ProfilePictureResult
import com.google.firebase.storage.ListResult
import com.google.firebase.storage.StorageReference
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class StorageService @Inject constructor(firebase: FirebaseClient){
    private val storageRef = firebase.storage.reference
    private val userUid = firebase.auth.currentUser!!.uid

    private fun getStorageReference(): StorageReference{
        return storageRef.child(PICTURES_PATH).child(userUid)
    }

    suspend fun uploadProfilePicture(fileName: String, filePath: Uri):Boolean = runCatching{
        val fileRef = getStorageReference().child(fileName)
        val uploadTask = fileRef.putFile(filePath)
        uploadTask.await()
    }.isSuccess

    suspend fun getUserPicture(): ProfilePictureResult {
        val listResult: ListResult = getStorageReference().list(1).await()

        val result = listResult.items[0].downloadUrl.await()
        return toProfilePictureResult(result)
    }

    private fun toProfilePictureResult(uri: Uri): ProfilePictureResult {
        return ProfilePictureResult.Success(uri.toString())
    }

    companion object{
        const val PICTURES_PATH = "pictures"
    }
}