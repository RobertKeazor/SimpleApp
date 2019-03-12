package com.robertkeazor.chatapp.manager

import android.content.Context
import android.util.Log
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.robertkeazor.chatapp.model.User
import io.reactivex.Single
import javax.inject.Inject

class UserDocRefManager @Inject constructor(val context: Context) {
    val id: String = ""
    val UsersCollectionKey = "users"
    private val auth = FirebaseAuth.getInstance()
    private val userDocRef =  FirebaseFirestore
        .getInstance()
        .collection(UsersCollectionKey)

    fun registerUser(userName: String, email: String, password: String) =
        Single.create<Task<AuthResult>> {
            val apiInstanceCode = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(context)
            if (apiInstanceCode == ConnectionResult.SUCCESS) {
                auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener { result ->
                        if (result.isSuccessful) { it.onSuccess(result) }
                        else if (result.isCanceled) { it.onError(Throwable("Authentication was canceled")) }
                    }
                    .addOnFailureListener { failure ->
                        Log.v("Register", failure.localizedMessage)
                        it.onError(failure)
                    }
            } else it.onError(Throwable("Google Play Issue"))
        }

    fun loginUser(email: String, password: String) = Single.create<Task<AuthResult>> {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { result ->
                if (result.isSuccessful) {
                    it.onSuccess(result)
                } else if (result.isCanceled) {
                    it.onError(Throwable("Sign In Failed"))
                }
            }
    }

    fun createUser(user: User): Single<User> =
        Single.create {
            userDocRef
                .document(user.userId)
                .set(user)
                .addOnSuccessListener { o ->
                    Log.v("UserCreated", "Created")
                    it.onSuccess(user)
                }
                .addOnFailureListener { e ->
                    it.onError(e)
                    Log.v("UserCreated", e.localizedMessage)
                }
        }

    fun retrieveUser(userId: String): Single<User> = Single.create {
        userDocRef
            .document(userId)
            .get()
            .addOnSuccessListener { snapshot ->
                it.onSuccess( snapshot.toObject(User::class.java)!!)
            }
            .addOnFailureListener {e ->
                it.onError(e)
            }
    }
}
