package dev.thedukerchip.domain

import dev.thedukerchip.domain.models.UserEntity
import dev.thedukerchip.domain.repository.UserRepository

class GetUserListUseCase(
    private val userRepository: UserRepository,
    private val saveUsersIntoDbUseCase: SaveUsersIntoDbUseCase
) {

    suspend operator fun invoke(page: Int): List<UserEntity> {
        return userRepository.getUsers(page).also {
            saveUsersIntoDbUseCase.invoke(it)
        }
    }
}

class SaveUsersIntoDbUseCase(
    private val userRepository: UserRepository,
) {

    suspend operator fun invoke(users: List<UserEntity>) {
        userRepository.saveUsersInLocalStorage(users)
    }
}

class GetUserByIdUseCase(
    private val userRepository: UserRepository,
) {

    suspend operator fun invoke(id: String): UserEntity? {
        return userRepository.getUserById(id)
    }
}