package dev.thedukerchip.domain.usecase

import com.zoho.people.core.model.data.User
import dev.thedukerchip.domain.repository.UserRepository

class GetUserListUseCase(
    private val userRepository: UserRepository,
    private val saveUsersIntoDbUseCase: SaveUsersIntoDbUseCase
) {

    suspend operator fun invoke(page: Int): List<User> {
        return userRepository.getUsers(page).also {
            saveUsersIntoDbUseCase.invoke(it)
        }
    }
}