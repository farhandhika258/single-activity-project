package com.example.myapplication.di

import com.example.myapplication.App
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@Component(
        modules = [
            AndroidInjectionModule::class,
            AndroidSupportInjectionModule::class,
            SingleActivityModule::class
        ]
)
interface MyAppComponent : AndroidInjector<App> {

    @Component.Builder
    interface Builder {
        fun build(): MyAppComponent
    }
}