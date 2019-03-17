package com.robertkeazor.chatapp.ui.main.login

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.constraintlayout.motion.widget.MotionLayout
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
                sharedVm.user = it
                val direction = LoginFragmentDirections.actionMainFragmentToChatFragment()
                findNavController().navigate(direction)
            }
        })

        vm.showSnackbarEvent.observe(this, Observer {
            it.getContentIfNotHandled {
                Snackbar.make(main, it,Snackbar.LENGTH_LONG).show()
            }
        })

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        main.setTransitionListener(object: MotionLayout.TransitionListener {
            override fun onTransitionTrigger(p0: MotionLayout?, p1: Int, p2: Boolean, p3: Float) {
                Log.v("Transition", "triggered")
            }

            override fun onTransitionStarted(p0: MotionLayout?, p1: Int, p2: Int) {
                Log.v("Transition", "started")
            }

            override fun onTransitionChange(p0: MotionLayout?, p1: Int, p2: Int, p3: Float) {
                Log.v("Transition", "changed")
            }

            override fun onTransitionCompleted(p0: MotionLayout?, p1: Int) {
                vm.isNewUser = !vm.isNewUser
            }
        }
        )
    }
}
