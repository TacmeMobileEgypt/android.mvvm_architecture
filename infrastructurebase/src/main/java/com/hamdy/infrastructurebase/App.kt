package com.hamdy.infrastructurebase

import android.app.Application
import android.content.Context
import android.content.res.Configuration
import com.android.fdf.utils.LocaleHelper
import com.hamdy.infrastructurebase.utils.LocaleHelperJava
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import timber.log.Timber


abstract class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            modules(getModules())
            androidContext(getAndroidContext())
        }

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        appInstance = this
    }

    abstract fun getAndroidContext(): Context

    private fun getModules(): List<Module> {

        return ArrayList<Module>().also {
            it.addAll(getModulesList())
        }
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        LocaleHelperJava.onAttach(this)
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext( LocaleHelperJava.onAttach(base))
    }

    abstract fun getModulesList(): List<Module>

    companion object{
        lateinit var appInstance: App

    }


}