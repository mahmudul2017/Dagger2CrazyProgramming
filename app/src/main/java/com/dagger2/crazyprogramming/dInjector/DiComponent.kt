package com.dagger2.crazyprogramming.dInjector

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        ActivityModule::class,
        DiModule::class
    ]
)
interface DiComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun create(app: Application): Builder
        fun build(): DiComponent
    }
    fun inject(diApplication: DiApplication)
}
