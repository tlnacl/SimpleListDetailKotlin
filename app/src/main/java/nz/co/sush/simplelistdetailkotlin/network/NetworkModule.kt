package nz.co.sush.simplelistdetailkotlin.network

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import nz.co.sush.simplelistdetailkotlin.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by tlnacl on 20/05/17.
 */
@Module
class NetworkModule {
    @Provides
    @Singleton
    fun provideApiAdapter(): ApiAdapter {
        val okHttpClient = OkHttpClient.Builder()
//                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .addNetworkInterceptor(StethoInterceptor())
                .build()

        val gson = GsonBuilder().create()
        val retrofit = Retrofit.Builder()
                .baseUrl(BuildConfig.API_ENDPOINT)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .build()
        return retrofit.create(ApiAdapter::class.java)
    }
}