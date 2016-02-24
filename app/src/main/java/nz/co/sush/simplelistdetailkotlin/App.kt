package nz.co.sush.simplelistdetailkotlin

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.facebook.stetho.Stetho
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.android.KodeinApplication
import com.github.salomonbrys.kodein.provider
import com.github.salomonbrys.kodein.singleton
import nz.co.sush.simplelistdetailkotlin.network.ApiAdapter

/**
 * Created by tomtang on 18/02/16.
 */
class App : Application(), KodeinApplication {

    override fun onCreate() {
        super.onCreate()

        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                        .build());
    }

    override val kodein = Kodein {
        bind<Context>() with singleton { applicationContext }
        bind<SharedPreferences>() with singleton { PreferenceManager.getDefaultSharedPreferences(applicationContext) }
        bind<ApiAdapter>() with provider { ApiAdapter.get() }
    }
}