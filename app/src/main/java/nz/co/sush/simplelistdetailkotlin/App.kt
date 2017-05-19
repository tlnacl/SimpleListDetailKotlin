package nz.co.sush.simplelistdetailkotlin

import android.app.Application
import com.facebook.stetho.Stetho
import timber.log.Timber

/**
 * Created by tomtang on 18/02/16.
 */
class App : Application() {

    val appComponent: AppComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                        .build())
    }
}