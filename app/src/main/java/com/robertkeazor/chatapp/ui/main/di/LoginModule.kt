package com.robertkeazor.chatapp.ui.main.di

import com.robertkeazor.chatapp.ui.main.login.LoginFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class LoginModule {
    @ContributesAndroidInjector
    abstract fun LoginFragment(): LoginFragment

}
