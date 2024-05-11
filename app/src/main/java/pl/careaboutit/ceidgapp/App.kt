package pl.careaboutit.ceidgapp

import android.app.Application
import pl.careaboutit.ceidgapp.di.Graph
import timber.log.Timber

class App : Application() {
    override fun onCreate() {
        Timber.plant(Timber.DebugTree())
        super.onCreate()
        Graph.provide(this)
    }
}