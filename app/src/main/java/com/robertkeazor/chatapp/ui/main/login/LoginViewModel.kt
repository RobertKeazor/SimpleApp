package com.robertkeazor.chatapp.ui.main.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.robertkeazor.chatapp.base.lifecycle.Event
import com.robertkeazor.chatapp.manager.PrefManager
import com.robertkeazor.chatapp.model.User
import com.robertkeazor.chatapp.usecase.LoginUseCase
import com.robertkeazor.chatapp.usecase.RegisterUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    val registerUseCase: RegisterUseCase,
    val loginUseCase: LoginUseCase, prefManager: PrefManager) : ViewModel() {
    val userName = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    val email = MutableLiveData<String>()

    val disposables = CompositeDisposable()
    val enterChatScreenEvent = MutableLiveData<Event<User>>()
    val showSnackbarEvent = MutableLiveData<Event<String>>()
    var isNewUser = true

    init {
        val userId = prefManager.getUserId()

        if (!userId.isNullOrEmpty()) {
            loginUseCase.getUser(userId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onRegisterSuccess, this::onRegisterFail)
        }
    }

    fun onRegisterButtonClick() {
        if (hasValidFields(userName.value, email.value, password.value)) {
            disposables.add(
                registerUseCase.register(userName.value!!, email.value!!, password.value!!)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(this::onRegisterSuccess, this::onRegisterFail)
            )
        } else {
            showSnackbarEvent.value = Event("No Field should be empty")
        }
    }

    fun  onLoginButtonClick() {
        if (hasValidFields(email.value, password.value)) {
           disposables.add (loginUseCase.login(email.value!!, password.value!!)
               .observeOn(AndroidSchedulers.mainThread())
               .subscribe(this::onRegisterSuccess, this::onRegisterFail)
           )
        }
    }

    fun onSubmitButtonClick() {
        if (isNewUser) onRegisterButtonClick() else onLoginButtonClick()
    }

    fun loginState() {
        showSnackbarEvent.value = Event("IsFale")
       isNewUser = false

    }

    fun registerState() {
        showSnackbarEvent.value = Event("IsTRUE")
        isNewUser = true
    }

    fun showChatRoom(user: User) {
        enterChatScreenEvent.value = Event(user)
    }

    fun onRegisterSuccess(user: User) {
        showChatRoom(user)
    }

    fun onRegisterFail(e: Throwable) {
        showSnackbarEvent.value = Event(e.localizedMessage)
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
