package com.robertkeazor.chatapp.ui.main.login

import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.robertkeazor.chatapp.R
import com.robertkeazor.chatapp.base.BaseFragment
import kotlinx.android.synthetic.main.register.*

class LoginFragment : BaseFragment<LoginViewModel>() {
    override val layout = R.layout.register

    override fun getViewModelClass() = LoginViewModel::class.java

    override fun setObservers() {
        vm.enterChatScreenEvent.observe(this, Observer {
            it.getContentIfNotHandled {
               val direction = LoginFragmentDirections.actionMainFragmentToChatFragment(it)
                direction.userId = it
                findNavController().navigate(direction)
            }
        })

        vm.showSnackbarEvent.observe(this, Observer {
            it.getContentIfNotHandled {
                Snackbar.make(main, it,Snackbar.LENGTH_LONG).show()
            }
        })
    }
}
