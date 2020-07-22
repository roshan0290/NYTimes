package com.nytimes.mvvm

import android.app.Application
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import com.nytimes.BuildConfig
import com.nytimes.data.network.MyApi
import com.nytimes.data.network.NetworkConnectionInterceptor
import com.nytimes.data.repositories.UserRepository
import com.nytimes.ui.MainViewModelFactory
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton
import timber.log.Timber

class MVVMApplication : Application(), KodeinAware {

    override val kodein = Kodein.lazy {
        import(androidXModule(this@MVVMApplication))

        bind() from singleton { NetworkConnectionInterceptor(instance()) }
        bind() from singleton { MyApi(instance()) }
        bind() from singleton { UserRepository(instance()) }
        bind() from provider {MainViewModelFactory(instance())}

    }

    //for logging
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

}