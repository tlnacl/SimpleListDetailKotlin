package nz.co.sush.simplelistdetailkotlin

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by tlnacl on 20/05/17.
 */
@Module
class AppModule(private val context: Context) {

    @Singleton @Provides fun provideContext(): Context = context

}