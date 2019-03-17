package com.robertkeazor.chatapp.usecase

import com.robertkeazor.chatapp.manager.ChatDocRefManager
import com.robertkeazor.chatapp.model.Message
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SendMessageUseCase @Inject constructor(val chatDocRefManager: ChatDocRefManager) {

    fun sendMessage(userName: String, msg: String) = chatDocRefManager.sendMessage(Message(userName, msg))
        .subscribeOn(Schedulers.io())

}