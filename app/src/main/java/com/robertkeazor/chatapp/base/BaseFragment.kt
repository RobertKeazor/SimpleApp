package com.robertkeazor.chatapp.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import dagger.android.support.DaggerFragment
import javax.inject.Inject
import kotlin.reflect.KClass

abstract class BaseFragment<T: ViewModel, Y: Class<ViewModel>> : DaggerFragment() {
    @Inject lateinit var viewModelFractory: ViewModelProvider.Factory
    lateinit var vm: T
    abstract val layout: Int






}