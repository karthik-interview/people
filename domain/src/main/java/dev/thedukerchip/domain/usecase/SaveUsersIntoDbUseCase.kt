package dev.thedukerchip.domain.usecase

import com.zoho.people.core.model.data.User
import dev.thedukerchip.domain.repository.UserRepository

class SaveUsersIntoDbUseCase(
    private val userRepository: UserRepository,
) {

    suspend operator fun invoke(users: List<User>) {
        userRepository.saveUsersInLocalStorage(users)
    }
}