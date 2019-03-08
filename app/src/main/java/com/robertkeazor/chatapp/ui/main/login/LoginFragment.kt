package com.robertkeazor.chatapp.ui.main.login

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import com.robertkeazor.chatapp.BR
import com.robertkeazor.chatapp.R
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class LoginFragment : DaggerFragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var vm: LoginViewModel

    companion object {
        fun newInstance() = LoginFragment()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        vm = ViewModelProviders.of(this, viewModelFactory)[LoginViewModel::class.java]
        val binding: ViewDataBinding = DataBindingUtil.inflate(inflater, R.layout.main_fragment, container, false)
        binding.setVariable(BR.vm, vm)
        return binding.root
    }
}
