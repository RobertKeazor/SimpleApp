package com.robertkeazor.chatapp.base.lifecycle

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView

abstract class KombindAdapter<VH: KombindAdapter.ViewHolder>(protected val items: MutableLiveArrayList<*>) : RecyclerView.Adapter<VH>() {
    private lateinit var layoutInflater: LayoutInflater
    protected abstract fun getLayout(position: Int): Int
    private var baseOwner: LifecycleOwner? = null
    open fun getHandler(position: Int): Any? = null

    open fun registerObserver(lifecycleOwner: LifecycleOwner): KombindAdapter<VH> {
        items.observe(lifecycleOwner, Observer {
            while(it?.isNotEmpty() == true) {
                val action = it.remove()
                when (action) {
                    is AdapterAction.NotifyItemRangeInserted -> notifyItemRangeInserted(action.positionStart, action.itemCount)
                    is AdapterAction.NotifyItemRangeRemoved -> notifyItemRangeRemoved(action.positionStart, action.itemCount)
                    is AdapterAction.NotifyItemRangeChanged -> notifyItemRangeChanged(action.positionStart, action.itemCount)
                    AdapterAction.NotifyDataSetChanged -> notifyDataSetChanged()
                }
            }
        })
        baseOwner = lifecycleOwner
        return this
    }

    override fun getItemCount() = items.size

    override fun getItemViewType(position: Int) = getLayout(position)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        if (!::layoutInflater.isInitialized) layoutInflater = LayoutInflater.from(parent.context)
        return createViewHolder(DataBindingUtil.inflate(layoutInflater, viewType, parent, false))
    }

    @Suppress("UNCHECKED_CAST")
    open fun createViewHolder(viewDataBinding: ViewDataBinding) = ViewHolder(viewDataBinding) as VH

    override fun onBindViewHolder(holder: VH, position: Int) {
        val  map = provideBindings(position)
        if (map.isNotEmpty()) holder.bind(map, baseOwner)
    }

    abstract fun provideBindings(position: Int): MutableMap<Int, *>

    open class ViewHolder(private val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
        open fun bind(bindings: Map<Int, *>, owner: LifecycleOwner?) {
            bindings.forEach { binding.setVariable(it.key, it.value) }
            if (owner != null) binding.lifecycleOwner = owner
            binding.executePendingBindings()
        }
    }
}
