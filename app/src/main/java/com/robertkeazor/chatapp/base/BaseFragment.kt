package com.robertkeazor.chatapp.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.robertkeazor.chatapp.BR
import com.robertkeazor.chatapp.ui.SharedViewModel
import dagger.android.support.DaggerFragment
import javax.inject.Inject

abstract class BaseFragment<T: ViewModel> : DaggerFragment() {
    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var vm: T
    lateinit var sharedVm:  SharedViewModel
    abstract val layout: Int

    abstract fun getViewModelClass(): Class<T>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        vm = ViewModelProviders.of(this, viewModelFactory)[getViewModelClass()]
        sharedVm = activity!!.run {
            ViewModelProviders.of(this).get(SharedViewModel::class.java)
        }
        val binding: ViewDataBinding = DataBindingUtil.inflate(inflater, layout, container, false)
        binding.setVariable(BR.vm, vm) //This assumes that the binding variable in xml will be called "vm"
        binding.lifecycleOwner = this
        setObservers()
        return binding.root
    }

    /**
     *  Override when you want to set LiveDataObservers
     */
    open fun setObservers() {}

    fun goto(directionId: Int) {
        findNavController().navigate(directionId)
    }
}
