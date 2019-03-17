package com.robertkeazor.chatapp.ui

import androidx.lifecycle.ViewModel
import com.robertkeazor.chatapp.model.User

class SharedViewModel : ViewModel() {
    lateinit var user: User
}