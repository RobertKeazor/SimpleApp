package com.robertkeazor.chatapp.manager

import android.content.Context
import android.util.Log
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.robertkeazor.chatapp.model.User
import io.reactivex.Completable
import io.reactivex.schedulers.Schedulers

import javax.inject.Inject

class FirebaseManager @Inject constructor(val context: Context) {

    val id: String = ""

    fun registerUser(userName: String, email: String, password: String) =
        Completable.create {
            val apiInstanceCode = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(context)
            if (apiInstanceCode == ConnectionResult.SUCCESS) {
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener { result ->
                        if (result.isSuccessful) {
                            Log.v("RegisterUser", "Is SuccessFul")
                            createUser(User(userName, email, result.result!!.user.uid))
                            it.onComplete()
                        }
                        else if (result.isCanceled) {
                            Log.v("RegisterUser", "Is Canceled")
                            it.onError(Throwable("Authentication was canceled"))
                        }
                    }
                    .addOnFailureListener { failure ->
                        Log.v("Register", failure.localizedMessage)
                        it.onError(failure)
                    }
            } else it.onError(Throwable("Google Play Issue"))
        }
            .subscribeOn(Schedulers.io())

    fun createUser(user: User) = FirebaseFirestore.getInstance().apply {
        collection("users")
            .document(user.userId)
            .set(user)
            .addOnSuccessListener {
                Log.v("UserCreated", "Created")
            }
            .addOnFailureListener {
                Log.v("UserCreated", it.localizedMessage)
            }
    }

}
