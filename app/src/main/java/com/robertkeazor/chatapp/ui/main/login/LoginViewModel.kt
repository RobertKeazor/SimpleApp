package com.robertkeazor.chatapp.ui.main.login

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.robertkeazor.chatapp.manager.FirebaseManager
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class LoginViewModel @Inject constructor(val firebaseManager: FirebaseManager) : ViewModel() {
    val userName = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    val registerError = MutableLiveData<String>()
    val disposables = CompositeDisposable()

    fun onRegisterButtonClick() {
        disposables.add(
            firebaseManager.registerUser(userName.value!!, password.value!!)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete { if (registerError.value.isNullOrEmpty()) registerError.value = null }
                .doOnError { registerError.value = it.localizedMessage }
                .onErrorComplete()
                .subscribe()
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }
}
