package com.dagger2.crazyprogramming.dInjector

import com.dagger2.crazyprogramming.view.DiFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {
    @ContributesAndroidInjector
    abstract fun contributeDaggerFragment(): DiFragment
}