package app.junsu.wwwe.global.security

import app.junsu.wwwe.domain.entity.user.User
import app.junsu.wwwe.domain.repository.user.UserRepository
import app.junsu.wwwe.exception.ServerException.UserNotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class SecurityFacade(
    @Autowired private val userRepository: UserRepository,
) {

    @Transactional
    internal fun getCurrentUser(): User {

        val email = SecurityContextHolder.getContext().authentication.name

        return userRepository.findByEmail(email) ?: throw UserNotFoundException()
    }
}
