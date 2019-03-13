package com.robertkeazor.chatapp.ui.main.chat

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
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
            vm.sendMessage(text_box.text.toString())
            text_box.setText("")
        }
        chat.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = ChatAdapter(vm).also { it.registerObserver(this@ChatFragment) }

        }
    }
}
