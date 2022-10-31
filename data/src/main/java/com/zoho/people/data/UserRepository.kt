package com.zoho.people.data

import com.zoho.people.core.model.data.User
import com.zoho.people.data.local.UserDao
import com.zoho.people.data.remote.UserService
import com.zoho.people.data.util.toDomainUserEntity
import com.zoho.people.data.util.toLocalUserEntity
import com.zoho.people.data.util.toUserEntity
import dev.thedukerchip.domain.repository.UserRepository

class UserRepositoryImpl(
    private val userService: UserService,
    private val userDao: UserDao,
) : UserRepository {

    override suspend fun getUsers(page: Int): List<User> {
        return userService
            .getUsers(page)
            .results
            .map { it.toDomainUserEntity() }
    }

    override suspend fun saveUsersInLocalStorage(users: List<User>) {
        with(users.map { it.toLocalUserEntity() }.toTypedArray()) {
            userDao.insertAll(*this)
        }
    }

    override suspend fun getUserById(id: String): User? {
        // TODO Handle user not found case
        return userDao.findById(id)?.toUserEntity()
    }
}