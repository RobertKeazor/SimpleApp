package com.robertkeazor.chatapp.ui.main.login


import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.robertkeazor.chatapp.base.lifecycle.Event
import com.robertkeazor.chatapp.manager.UserDocRefManager
import com.robertkeazor.chatapp.usecase.LoginUseCase
import com.robertkeazor.chatapp.usecase.RegisterUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    val firebaseRefManager: UserDocRefManager,
    val registerUseCase: RegisterUseCase,
    val loginUseCase: LoginUseCase) : ViewModel() {
    val userName = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    val email = MutableLiveData<String>()
    val registerError = MutableLiveData<String>()
    val disposables = CompositeDisposable()
    val enterChatScreenEvent = MutableLiveData<Event<Boolean>>()


    fun onRegisterButtonClick() {
       disposables.add(registerUseCase.register(userName.value!!, email.value!!, password.value!!)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({user -> Log.v("RegisterResult", user.userId)}, {e -> Log.v("RegisterResult", e.localizedMessage)}))

    }

    fun showChatRoom() {
        enterChatScreenEvent.value = Event(true)
    }

    fun hasValidFields(vararg fieldValues: String?): Boolean {
        fieldValues.forEach { if (it.isNullOrEmpty()) return false }
        return true
    }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }
}
