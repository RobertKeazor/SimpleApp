package com.robertkeazor.chatapp.ui.main

import android.os.Bundle
import android.view.View
import com.robertkeazor.chatapp.R
import com.robertkeazor.chatapp.base.BaseFragment
import kotlinx.android.synthetic.main.chat_fragment.*

class ChatFragment : BaseFragment<ChatViewModel>() {
    override val layout = R.layout.chat_fragment

    override fun getViewModelClass() = ChatViewModel::class.java

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        animation_view.setOnClickListener {
            animation_view.playAnimation()

        }
    }
}
