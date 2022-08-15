package com.sanket.androidplayground2.di.module

import android.content.Context
import androidx.room.Room
import com.sanket.androidplayground2.BuildConfig
import com.sanket.androidplayground2.data.api.ApiConstants
import com.sanket.androidplayground2.data.api.ApiHelper
import com.sanket.androidplayground2.data.api.ApiHelperImpl
import com.sanket.androidplayground2.data.api.ApiService
import com.sanket.androidplayground2.data.db.AppDatabase
import com.sanket.androidplayground2.data.db.DatabaseHelper
import com.sanket.androidplayground2.data.db.DatabaseHelperImpl
import com.sanket.androidplayground2.widgets.internet_observer.ConnectivityObserver
import com.sanket.androidplayground2.widgets.internet_observer.NetworkConnectivityObserver
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

private const val ANDROID_PLAYGROUND = "ANDROID_PLAYGROUND"

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {


    @Provides
    fun provideHiltExampleBaseUrl() = ApiConstants.HILT_EXAMPLE_URL

    @Provides
    @Singleton
    fun provideOkHttpClient() = if (BuildConfig.DEBUG) {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()
    } else {
        OkHttpClient.Builder().build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient, BASE_URL: String): Retrofit =
        Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit) = retrofit.create(ApiService::class.java)

    @Provides
    @Singleton
    fun provideApiHelper(apiHelper: ApiHelperImpl): ApiHelper = apiHelper

    @Provides
    @Singleton
    fun provideDBHelper(dbHelper: DatabaseHelperImpl): DatabaseHelper = dbHelper

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            ANDROID_PLAYGROUND
        ).build()

    @ApiKey
    @Provides
    @Singleton
    fun provideApiKey(): String = "My ApiKey"

    @LibraryKey
    @Provides
    @Singleton
    fun provideLibraryKey(): String = "My Library Key"

    @Provides
    @Singleton
    fun provideConnectivityObserver(@ApplicationContext context: Context): ConnectivityObserver =
        NetworkConnectivityObserver(context)

}