package com.robertkeazor.chatapp.base.lifecycle

import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableList
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import java.util.Queue
import java.util.concurrent.LinkedBlockingQueue

open class MutableLiveArrayList<T: Any>(default: Collection<T>? = null) : ObservableArrayList<T>() {
    private val adapterActionQueue = LinkedBlockingQueue<AdapterAction>()
    private val adapterAction = MutableLiveData<Queue<AdapterAction>>()

    init {
        if (default != null)  addAll(default)
        addOnListChangedCallback(object : ObservableList.OnListChangedCallback<ObservableArrayList<T>>() {
            override fun onItemRangeRemoved(sender: ObservableArrayList<T>?, positionStart: Int, itemCount: Int) = addAdapterAction(AdapterAction.NotifyItemRangeRemoved(positionStart, itemCount))
            override fun onChanged(sender: ObservableArrayList<T>?) = addAdapterAction(AdapterAction.NotifyDataSetChanged)
            override fun onItemRangeMoved(sender: ObservableArrayList<T>?, positionStart: Int, toPosition: Int, itemCount: Int) = addAdapterAction(AdapterAction.NotifyItemRangeChanged(positionStart, itemCount))
            override fun onItemRangeInserted(sender: ObservableArrayList<T>?, positionStart: Int, itemCount: Int) = addAdapterAction(AdapterAction.NotifyItemRangeInserted(positionStart, itemCount))
            override fun onItemRangeChanged(sender: ObservableArrayList<T>?, positionStart: Int, itemCount: Int) = addAdapterAction(AdapterAction.NotifyItemRangeChanged(positionStart, itemCount))
        })
    }

    private fun addAdapterAction(action: AdapterAction) {
        adapterActionQueue.offer(action)
        adapterAction.value = adapterActionQueue
    }

    fun observe(lifecycleOwner: LifecycleOwner, observer: Observer<Queue<AdapterAction>>) {
        adapterAction.observe(lifecycleOwner, observer)
    }
}
