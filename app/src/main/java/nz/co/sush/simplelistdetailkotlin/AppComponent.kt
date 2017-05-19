package nz.co.sush.simplelistdetailkotlin

import dagger.Component
import nz.co.sush.simplelistdetailkotlin.network.NetworkModule
import nz.co.sush.simplelistdetailkotlin.ui.activities.MainActivity
import javax.inject.Singleton

/**
 * Created by tlnacl on 20/05/17.
 */
@Component(
        modules = arrayOf(
                AppModule::class,
                NetworkModule::class
        )
)
@Singleton
interface AppComponent {
    fun inject(into: MainActivity)
}