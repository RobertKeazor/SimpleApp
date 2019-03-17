package com.robertkeazor.chatapp.usecase

import com.robertkeazor.chatapp.manager.ChatDocRefManager
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class GetChatUseCase @Inject constructor(val chatDocRefManager: ChatDocRefManager) {
    fun getChat() = chatDocRefManager.retreiveChat()
        .subscribeOn(Schedulers.io())
}