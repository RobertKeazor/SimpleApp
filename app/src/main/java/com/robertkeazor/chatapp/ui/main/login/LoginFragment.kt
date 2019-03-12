package com.robertkeazor.chatapp.ui.main.login

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.robertkeazor.chatapp.BR
import com.robertkeazor.chatapp.R
import com.robertkeazor.chatapp.base.BaseFragment
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class LoginFragment : BaseFragment<LoginViewModel>() {
    override val layout = R.layout.register

    override fun getViewModelClass() = LoginViewModel::class.java

    override fun setObservers() {
        vm.enterChatScreenEvent.observe(this, Observer {
            it.getContentIfNotHandled {
                goto(R.id.action_mainFragment_to_chatFragment)
            }
        })
    }
}
