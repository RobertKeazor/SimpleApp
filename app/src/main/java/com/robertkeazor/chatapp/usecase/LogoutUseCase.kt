package com.robertkeazor.chatapp.usecase

import com.robertkeazor.chatapp.manager.PrefManager
import com.robertkeazor.chatapp.manager.UserDocRefManager
import javax.inject.Inject

class LogoutUseCase @Inject constructor(val userDocRefManager: UserDocRefManager, val prefManager: PrefManager) {

    fun logout() {
        userDocRefManager.logoutUser()
        prefManager.clearUserInfo()
    }
}