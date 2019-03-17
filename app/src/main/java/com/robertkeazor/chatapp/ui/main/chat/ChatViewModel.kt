package com.robertkeazor.chatapp.ui.main.chat

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.robertkeazor.chatapp.base.lifecycle.Event
import com.robertkeazor.chatapp.base.lifecycle.MutableLiveArrayList
import com.robertkeazor.chatapp.model.Message
import com.robertkeazor.chatapp.usecase.ChatUpdateUseCase
import com.robertkeazor.chatapp.usecase.SendMessageUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class ChatViewModel @Inject constructor(val sendMessageUseCase: SendMessageUseCase,
    chatUpdateUseCase: ChatUpdateUseCase)  : ViewModel() {
    val items = MutableLiveArrayList<Message>()
    val compositeDisposable = CompositeDisposable()
    val closeKeyboardEvent = MutableLiveData<Event<Boolean>>()
    lateinit var userName: String

    init {
        compositeDisposable.add(chatUpdateUseCase.onUpdate()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({msg -> items.add(msg)
                closeKeyboardEvent.value = Event(true) }, { e -> Log.v("Failure", e.localizedMessage)}))
    }

    fun sendMessage(message: String) {
        compositeDisposable.add(sendMessageUseCase.sendMessage(userName, message)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({closeKeyboardEvent.value = Event(true)}, { e -> Log.v("Failure", e.localizedMessage)}))
      }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}
