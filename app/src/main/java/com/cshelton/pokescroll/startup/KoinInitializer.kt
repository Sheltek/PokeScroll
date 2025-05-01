package com.cshelton.pokescroll.startup

import android.content.Context
import androidx.startup.Initializer
import com.cshelton.pokescroll.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.dsl.KoinAppDeclaration

class KoinInitializer : Initializer<KoinApplication> {

    override fun create(context: Context): KoinApplication {
        val koinApplication = startKoin {
            androidContext(context)
            printLogger(Level.DEBUG)
            modules(appModule)
        }
        return koinApplication
    }

    override fun dependencies(): List<Class<out Initializer<*>>> = emptyList()
}