package com.dagger2.crazyprogramming.dInjector

import com.dagger2.crazyprogramming.view.DiActivity
import com.dagger2.crazyprogramming.view.PostActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {
    @ContributesAndroidInjector
    abstract fun contributeDaggerDiActivity(): DiActivity

    @ContributesAndroidInjector
    abstract fun contributeDaggerPostActivity(): PostActivity
}