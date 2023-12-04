package com.armandorochin.firebaseshowcaseapp.core

import android.content.Context
import java.io.File

fun Context.createImageFile(email:String): File {
//    return File.createTempFile(
//        email,
//        ".jpg",
//        externalCacheDir
//    )
    return File(externalCacheDir,"$email.jpg")
}