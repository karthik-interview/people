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
                email = it.email,
                first = it.name.first,
                last = it.name.last,
                profileUri = it.picture.large,
                profileThumbnailUri = it.picture.thumbnail,
                phone = it.phone,
                dob = it.dob.date,
                age = it.dob.age,
                streetName = it.location.street.name,
                streetNo = it.location.street.number,
                city = it.location.city,
                state = it.location.state,
                country = it.location.country,
                postcode = it.location.postcode,
                latitude = it.location.coordinates.latitude,
                longitude = it.location.coordinates.longitude,
            )
        }
        userDao.insertAll(*localMapping.toTypedArray())
    }

    suspend fun getUserById(id: String): UserEntity {
        // TODO Handle user not found case
        val user = userDao.findById(id)
        return UserEntity(
            firstName = user.first,
            lastName = user.last,
            email = user.email,
            profileUri = user.profileUri,
            profileThumbnailUri = user.profileThumbnailUri,
            phone = user.phone,
            dob = user.dob,
            age = user.age,
            streetName = user.streetName,
            streetNo = user.streetNo,
            city = user.city,
            state = user.state,
            country = user.country,
            postcode = user.postcode,
        )
    }
}