package com.robertkeazor.chatapp.base

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
        ViewModelModule::class
    ]
)
@Singleton
interface MainApplicationComponent {
    fun inject(app: MainApplication)


    @Component.Builder
    interface Builder {
        @BindsInstance
        fun app(app: MainApplication): Builder

        fun build(): MainApplicationComponent
    }
}