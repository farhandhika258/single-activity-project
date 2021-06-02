package com.example.myapplication

import com.example.myapplication.di.DaggerAndroidPlaygroundComponent
import com.example.myapplication.libs.Const
import com.example.myapplication.services.CryptoService
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import io.realm.Realm
import io.realm.RealmConfiguration
import javax.inject.Inject

class App : DaggerApplication() {

    @Inject lateinit var cryptoService: CryptoService

    val realmVersion = 1L

    override fun applicationInjector(): AndroidInjector<App> {
        return DaggerAndroidPlaygroundComponent.builder()
                .build()
    }

    override fun onCreate() {
        super.onCreate()
        Realm.init(this)

        val realmConfig = RealmConfiguration.Builder()
                .name(Const.REALM_DEFAULT_NAME)
                .schemaVersion(realmVersion)
                .deleteRealmIfMigrationNeeded()
                .build()
        Realm.setDefaultConfiguration(realmConfig)
    }
}
