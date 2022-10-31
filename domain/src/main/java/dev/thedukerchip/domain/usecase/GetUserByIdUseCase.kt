package dev.thedukerchip.domain.usecase

import com.zoho.people.core.model.data.User
import dev.thedukerchip.domain.repository.UserRepository

class GetUserByIdUseCase(
    private val userRepository: UserRepository,
) {

    suspend operator fun invoke(id: String): User? {
        return userRepository.getUserById(id)
    }
}