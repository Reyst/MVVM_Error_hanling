package gsihome.reyst.mvvm.example

import android.app.Application
import gsihome.reyst.mvvm.example.di.connectedKoinModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(connectedKoinModules)
        }

//        registerBroadcasts()
    }


}