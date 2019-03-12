package com.robertkeazor.chatapp.ui.main.di

import com.robertkeazor.chatapp.ui.main.ChatFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ChatModule {
    @ContributesAndroidInjector
    abstract fun chatFragment(): ChatFragment
}