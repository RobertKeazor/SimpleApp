package com.robertkeazor.chatapp.ui.main.chat

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.lifecycle.Observer
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
        vm.userName = sharedVm.user.userName
        addObservers()
    }

    fun addObservers() {
        vm.closeKeyboardEvent.observe(this, Observer {
            it.getContentIfNotHandled {
                val imm = activity!!.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(text_box.windowToken, 0)
            }
        })
    }
}
