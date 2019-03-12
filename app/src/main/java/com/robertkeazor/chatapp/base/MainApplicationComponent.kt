package com.robertkeazor.chatapp.base

import android.content.Context
import com.robertkeazor.chatapp.ui.main.di.ChatModule
import com.robertkeazor.chatapp.ui.main.di.LoginModule
import com.robertkeazor.chatapp.ui.main.di.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton


@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        LoginModule::class,
        ViewModelModule::class,
        ChatModule::class
    ]
)
@Singleton
interface MainApplicationComponent {
    fun inject(app: MainApplication)


    @Component.Builder
    interface Builder {
        @BindsInstance
        fun app(app: MainApplication): Builder

        @BindsInstance
        fun context(context: Context): Builder

        fun build(): MainApplicationComponent
    }
}