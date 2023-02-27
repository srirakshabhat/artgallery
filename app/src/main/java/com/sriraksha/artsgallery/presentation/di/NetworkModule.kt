package com.sriraksha.artsgallery.presentation.di

import android.content.Context
import android.net.ConnectivityManager
import com.sriraksha.artsgallery.BuildConfig
import com.sriraksha.artsgallery.data.repository.datasource.ArtsGalleryAPIService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    fun providesRetrofitInstance(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BuildConfig.BASE_URL)
            .build()
    }

    @Singleton
    @Provides
    fun providesArtsGalleryApiService(retrofit: Retrofit): ArtsGalleryAPIService {
        return retrofit.create(ArtsGalleryAPIService::class.java)
    }

    @Singleton
    @Provides
    fun providesConnectivityManager(context: Context): ConnectivityManager {
        return context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }
}
