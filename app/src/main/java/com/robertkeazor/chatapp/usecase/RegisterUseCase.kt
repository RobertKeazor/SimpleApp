package com.robertkeazor.chatapp.usecase

import com.robertkeazor.chatapp.manager.UserDocRefManager
import com.robertkeazor.chatapp.model.User
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class RegisterUseCase @Inject constructor(val userDocRefManager: UserDocRefManager) {
    fun register(userName: String, email: String, password: String) =
        userDocRefManager.registerUser(userName, email,password)
            .subscribeOn(Schedulers.io())
            .flatMap { userDocRefManager.createUser(User(userName, email, it.result!!.user.uid)) }
}
