package com.robertkeazor.chatapp.ui.main.chat

import androidx.lifecycle.ViewModel
import com.robertkeazor.chatapp.base.lifecycle.MutableLiveArrayList
import com.robertkeazor.chatapp.model.Message
import javax.inject.Inject

class ChatViewModel @Inject constructor()  : ViewModel() {
    // TODO: Implement the ViewModel
    val hi = "Hello Chat"
    val items = MutableLiveArrayList<Message>()

    fun sendMessage(message: String) {
        items.add(Message("Name", message, "999", true))
    }
}
