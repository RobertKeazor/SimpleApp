package com.robertkeazor.chatapp.ui.main.chat

import com.robertkeazor.chatapp.BR
import com.robertkeazor.chatapp.R
import com.robertkeazor.chatapp.base.lifecycle.KombindAdapter

class ChatAdapter(val vm: ChatViewModel) : KombindAdapter<KombindAdapter.ViewHolder>(vm.items) {
    override fun getLayout(position: Int) = R.layout.item_chat

    override fun provideBindings(position: Int) = mutableMapOf(BR.item to items[position])
}
