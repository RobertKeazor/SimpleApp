package com.robertkeazor.chatapp.ui.main

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider

import com.robertkeazor.chatapp.R
import com.robertkeazor.chatapp.base.BaseFragment
import javax.inject.Inject

class ChatFragment : BaseFragment<ChatViewModel>() {
    override val layout = R.layout.chat_fragment

    override fun getViewModelClass() = ChatViewModel::class.java
}
