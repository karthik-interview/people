package dev.thedukerchip.domain.repository

import dev.thedukerchip.domain.models.UserEntity

interface UserRepository {

    suspend fun getUsers(page: Int): List<UserEntity>

    suspend fun saveUsersInLocalStorage(users: List<UserEntity>)

    suspend fun getUserById(id: String): UserEntity?

}