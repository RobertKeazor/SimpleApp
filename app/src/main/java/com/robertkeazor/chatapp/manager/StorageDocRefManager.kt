package com.robertkeazor.chatapp.manager

import android.content.Context
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.UploadTask
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import io.reactivex.Single

class StorageDocRefManager @AssistedInject constructor(val context: Context, @Assisted val userId: String) {
    var storage = FirebaseStorage.getInstance()
    val profileDocDirectory = "$userId/profilepic"
    var profileImage = storage.reference.child(profileDocDirectory)

    fun createUserProfileReference(data: ByteArray): Single<Boolean> =
        Single.create {
            val uploadTask: UploadTask = profileImage.putBytes(data)
            uploadTask.addOnFailureListener { exception ->
                it.onError(exception)
            }.addOnSuccessListener {taskSnapshot ->
                it.onSuccess(true)

            }
        }

    fun getProfileImageData(): Single<ByteArray> = Single.create{
        profileImage.getBytes(1024 * 1024)
            .addOnSuccessListener { bytes ->
                it.onSuccess(bytes)
            }
            .addOnFailureListener { exception -> it.onError(exception)  }

    }

    @AssistedInject.Factory
    interface Factory {
        fun create(userId: String): StorageDocRefManager
    }
}
