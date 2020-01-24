package com.mte.baseinfrastructure

import android.content.Context
import com.mte.infrastructurebase.App
import org.koin.core.module.Module
import sa.revival.shortage.koin.appModule
import sa.revival.shortage.koin.repoModule
import sa.revival.shortage.koin.viewModule

class HamdyApp : App() {


    override fun getAndroidContext(): Context {
        return this
    }

    override fun getModulesList(): List<Module> {
        return listOf(appModule , viewModule , repoModule)
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }


    companion object {

        private var instance : HamdyApp? = null

    }
}