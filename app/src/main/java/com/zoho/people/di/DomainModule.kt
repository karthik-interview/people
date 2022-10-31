package com.zoho.people.di

import com.zoho.people.data.UserRepositoryImpl
import com.zoho.people.data.local.UserDao
import com.zoho.people.data.remote.UserService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.thedukerchip.domain.repository.UserRepository
import dev.thedukerchip.domain.usecase.GetUserByIdUseCase
import dev.thedukerchip.domain.usecase.GetUserListUseCase
import dev.thedukerchip.domain.usecase.SaveUsersIntoDbUseCase

@Module
@InstallIn(SingletonComponent::class)
class DomainModule {

    @Provides
    fun provideUserRepository(
        userService: UserService,
        userDao: UserDao
    ): UserRepository = UserRepositoryImpl(userService, userDao)

    @Provides
    fun provideGetUserByIdUseCase(userRepository: UserRepository): GetUserByIdUseCase {
        return GetUserByIdUseCase(userRepository)
    }

    @Provides
    fun provideGetUsersUseCase(
        userRepository: UserRepository,
        saveUsersIntoDbUseCase: SaveUsersIntoDbUseCase
    ): GetUserListUseCase {
        return GetUserListUseCase(userRepository, saveUsersIntoDbUseCase)
    }

    @Provides
    fun provideSaveUsersIntoDbUseCase(userRepository: UserRepository): SaveUsersIntoDbUseCase {
        return SaveUsersIntoDbUseCase(userRepository)
    }
}