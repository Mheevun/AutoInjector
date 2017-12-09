package com.ppwasin.autoinjector.integrationtest.di

import com.ppwasin.autoinjector.integrationtest.injector.InjectedActivity
import com.ppwasin.autoinjector.integrationtest.injector.InjectedFragment
import dagger.Component
import dagger.Module
import dagger.android.AndroidInjector
import dagger.android.ContributesAndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import dagger.android.support.DaggerApplication


/**
 * DaggerComponent for Testing
 */
@Component(modules = [AndroidSupportInjectionModule::class, ActivityBuilderModule::class])
//AndroidInjector: already has function fun inject(app)
interface TestComponent : AndroidInjector<DaggerApplication> {
    @Component.Builder
    interface Builder {
        fun build(): TestComponent
//        @BindsInstance
//        fun application(application: Application): TestComponent.Builder
    }
}

@Module
abstract class ActivityBuilderModule {

    /*generate subcomponent for this activity,
    should use when: its builder have no other methods or supertypes
    modules = modules to install into the subcomponent */
    @ContributesAndroidInjector(modules = [FragmentBuilderModule::class])
    abstract fun bindActivity(): InjectedActivity
}

@Module
abstract class FragmentBuilderModule {
    @ContributesAndroidInjector
    abstract fun bindFragment(): InjectedFragment
}