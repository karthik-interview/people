package com.zoho.people.data

import com.zoho.people.data.local.LocalUserEntity
import com.zoho.people.data.local.UserDao
import com.zoho.people.data.remote.UserService
import com.zoho.people.models.data.RemoteUserEntity
import com.zoho.people.models.mapper.UserMapper
import com.zoho.people.models.presentation.UserEntity


class UserRepository(
    private val userService: UserService,
    private val userMapper: UserMapper,
    private val userDao: UserDao,
) {

    suspend fun getUsers(page: Int): List<UserEntity> {
        val results = userService.getUsers(page).results
        saveUsersToLocal(results)
        return results.map { userMapper.toPresenter(it) }
    }

    private suspend fun saveUsersToLocal(users: List<RemoteUserEntity>) {
        val localMapping = users.map {
            LocalUserEntity(
                it.email,
                it.name.first,
                it.name.last,
                it.picture.large,
                it.picture.thumbnail
            )
        }
        userDao.insertAll(*localMapping.toTypedArray())
    }

    suspend fun getUserById(id: String): UserEntity {
        // TODO Handle user not found case
        val user = userDao.findById(id)
        return UserEntity(
            user.first,
            user.last,
            user.email,
            user.profileUri,
            user.profileThumbnailUri
        )
    }
}