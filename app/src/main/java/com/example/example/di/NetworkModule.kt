package com.example.example.di

import com.example.example.data.network.QuotesApi
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        // HttpLoggingInterceptor
        val okHttpClient = OkHttpClient.Builder()
        okHttpClient.addInterceptor(
            HttpLoggingInterceptor().setLevel(
                HttpLoggingInterceptor.Level.BODY
            )
        )
        return okHttpClient.build()
    }

    @Provides
    @Singleton
    fun provideQuotesApi(
        moshi: Moshi,
        okHttpClient: OkHttpClient
    ): QuotesApi {
        return Retrofit.Builder()
            .baseUrl("https://quotes.rest")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(okHttpClient)
            .build()
            .create(QuotesApi::class.java)
    }
}