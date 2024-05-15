package pl.careaboutit.ceidgapp

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import pl.careaboutit.ceidgapp.di.Graph
import pl.careaboutit.ceidgapp.di.appModule
import timber.log.Timber

class App : Application() {
    override fun onCreate() {
        Timber.plant(Timber.DebugTree())
        super.onCreate()

        startKoin {
            androidContext(this@App)
            androidLogger()
            modules(appModule)
        }

        Graph.provide(this)
    }
}