package com.robertkeazor.chatapp.usecase

import com.robertkeazor.chatapp.manager.PrefManager
import com.robertkeazor.chatapp.manager.UserDocRefManager
import com.robertkeazor.chatapp.model.User
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class RegisterUseCase @Inject constructor(val userDocRefManager: UserDocRefManager, val prefManager: PrefManager) {
    fun register(userName: String, email: String, password: String) =
        userDocRefManager.registerUser(email,password)
            .subscribeOn(Schedulers.io())
            .flatMap {
                prefManager.saveUserId(it.result!!.user.uid)
                prefManager.saveUserName(userName)
                userDocRefManager.createUser(User(userName, email, it.result!!.user.uid))
            }
}
