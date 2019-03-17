package com.robertkeazor.chatapp.ui

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.robertkeazor.chatapp.R

class MainActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
    }
}
