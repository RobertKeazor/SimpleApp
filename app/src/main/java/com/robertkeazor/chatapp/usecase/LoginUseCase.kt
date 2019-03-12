package com.robertkeazor.chatapp.usecase

import com.robertkeazor.chatapp.manager.UserDocRefManager
import com.robertkeazor.chatapp.model.User
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class LoginUseCase @Inject constructor(val userDocRefManager: UserDocRefManager) {
    fun login(email: String, password: String): Single<User> {
        return userDocRefManager.loginUser(email, password)
            .subscribeOn(Schedulers.io())
            .flatMap { userDocRefManager.retrieveUser(it.result!!.user.uid)}
    }
}
