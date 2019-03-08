package com.robertkeazor.chatapp.base

import android.app.Application
import androidx.fragment.app.Fragment

import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class MainApplication : Application(), HasSupportFragmentInjector  {
    @Inject
    lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>

    private var mAppComponent: MainApplicationComponent? = null

    override fun onCreate() {
        super.onCreate()
        setupGraph()
    }

    private fun setupGraph() {
        if (mAppComponent == null) {
            mAppComponent = DaggerMainApplicationComponent.builder()
                .app(this)
                .build()
            mAppComponent!!.inject(this)
        }

    }

    override fun supportFragmentInjector() = fragmentInjector
}
