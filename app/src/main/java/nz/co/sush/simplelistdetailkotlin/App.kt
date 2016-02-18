package nz.co.sush.simplelistdetailkotlin

import android.app.Application

/**
 * Created by tomtang on 18/02/16.
 */
class App : Application() {

    companion object {
        lateinit var instance: App
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}