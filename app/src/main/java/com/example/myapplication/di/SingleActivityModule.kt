package com.example.myapplication.di

import com.example.myapplication.domain.Announcer
import com.example.myapplication.ui.MainActivity
import com.example.myapplication.domain.Navigator
import com.example.myapplication.ui.AnnouncerImpl
import com.example.myapplication.ui.NavigatorImpl
import com.example.myapplication.ui.dashboard.DashboardFragment
import com.example.myapplication.ui.main.MainFragment
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface SingleActivityModule {

    @SingleActivity
    @ContributesAndroidInjector(
            modules = [
                FragmentModule::class,
                ActivityScopedModule::class
            ]
    )
    fun androidPlaygroundActivity(): MainActivity
}

@Module
interface FragmentModule {

    @ContributesAndroidInjector
    fun mainFragment(): MainFragment

    @ContributesAndroidInjector
    fun detailsFragment(): DashboardFragment
}

@Module
interface ActivityScopedModule {

    @Binds
    fun navigator(navigatorImpl: NavigatorImpl): Navigator

    @Binds
    fun announcer(announcerImpl: AnnouncerImpl): Announcer
}
