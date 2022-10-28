package com.zoho.people.data

import com.zoho.people.data.remote.UserService
import com.zoho.people.models.mapper.UserMapper
import com.zoho.people.models.presentation.UserEntity


class UserRepository(
    private val userService: UserService,
    private val userMapper: UserMapper,
) {

    suspend fun getUsers(page: Int): List<UserEntity> {
        return userService.getUsers(page).results.map { userMapper.toPresenter(it) }
    }
}