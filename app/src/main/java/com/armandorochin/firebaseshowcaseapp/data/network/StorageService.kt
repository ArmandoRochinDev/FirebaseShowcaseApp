package com.armandorochin.firebaseshowcaseapp.data.network

import android.net.Uri
import com.google.firebase.storage.ListResult
import com.google.firebase.storage.StorageReference
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class StorageService @Inject constructor(private val firebase: FirebaseClient){
    private val storageRef = firebase.storage.reference
    private val userUid = firebase.auth.currentUser!!.uid

    private fun getStorageReference(): StorageReference{
        return storageRef.child(PICTURES_PATH).child(userUid)
    }

    suspend fun uploadProfilePicture(fileName: String, filePath: Uri) = runCatching{
        val fileRef = getStorageReference().child(fileName)
        val uploadTask = fileRef.putFile(filePath)
        uploadTask.await()
    }.getOrNull()

    suspend fun getUserPicture(): String? {
        var imageUrl:String? = null
        val listResult: ListResult = getStorageReference().list(1).await()

        for (item in listResult.items){
            imageUrl = item.downloadUrl.await().toString()
        }

        return imageUrl
    }

    companion object{
        const val PICTURES_PATH = "pictures"
    }
}