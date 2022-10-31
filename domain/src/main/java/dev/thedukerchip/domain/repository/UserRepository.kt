package dev.thedukerchip.domain.repository

import com.zoho.people.core.model.data.User

interface UserRepository {

    suspend fun getUsers(page: Int): List<User>

    suspend fun saveUsersInLocalStorage(users: List<User>)

    suspend fun getUserById(id: String): User?

}