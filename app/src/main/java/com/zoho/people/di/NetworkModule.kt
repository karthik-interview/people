package com.zoho.people.di

import com.google.gson.Gson
import com.zoho.people.BuildConfig
import com.zoho.people.data.remote.UserService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    fun provideOkhttpClient(): OkHttpClient {
        val clientBuilder = OkHttpClient.Builder()

        if (BuildConfig.DEBUG) {
            val logging = HttpLoggingInterceptor().apply {
                setLevel(HttpLoggingInterceptor.Level.BODY)
            }

            clientBuilder.addInterceptor(logging)
        }

        return clientBuilder.build()
    }

    @Provides
    fun provideUserService(
        okHttpClient: OkHttpClient,
        gson: Gson
    ): UserService {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://randomuser.me/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        return retrofit.create(UserService::class.java)
    }

    @Provides
    fun provideGson(): Gson = Gson()

}