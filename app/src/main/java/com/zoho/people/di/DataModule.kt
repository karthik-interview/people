package com.zoho.people.di

import android.content.Context
import androidx.room.Room
import com.google.gson.Gson
import com.zoho.people.BuildConfig
import com.zoho.people.data.UserRepository
import com.zoho.people.data.local.UserDao
import com.zoho.people.data.local.UserDatabase
import com.zoho.people.data.remote.UserService
import com.zoho.people.models.mapper.UserMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    fun provideUserRepository(
        userService: UserService,
        userMapper: UserMapper,
        userDao: UserDao
    ) = UserRepository(userService, userMapper, userDao)

    @Provides
    fun provideUserMapper(): UserMapper = UserMapper()

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

    @Provides
    fun provideUserDao(userDatabase: UserDatabase): UserDao {
        return userDatabase.userDao()
    }

    @Provides
    fun provideUserDatabase(@ApplicationContext context: Context): UserDatabase {
        return Room.databaseBuilder(
            context,
            UserDatabase::class.java, "users"
        ).build()
    }
}