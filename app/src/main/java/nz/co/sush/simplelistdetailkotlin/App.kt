package nz.co.sush.simplelistdetailkotlin

import android.app.Application
import com.facebook.stetho.Stetho

/**
 * Created by tomtang on 18/02/16.
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()

        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                        .build())
    }
}