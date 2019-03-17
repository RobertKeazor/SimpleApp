package com.robertkeazor.chatapp.usecase

import com.robertkeazor.chatapp.manager.ChatDocRefManager
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ChatUpdateUseCase @Inject constructor(val chatDocRefManager: ChatDocRefManager) {
    fun onUpdate() = chatDocRefManager.chatUpdate()
        .subscribeOn(Schedulers.io())

}