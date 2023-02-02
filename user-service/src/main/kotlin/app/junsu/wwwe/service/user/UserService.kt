package app.junsu.wwwe.service.user

import app.junsu.wwwe.domain.entity.user.User
import app.junsu.wwwe.domain.repository.user.UserRepository
import app.junsu.wwwe.exception.ServerException.UserExistException
import app.junsu.wwwe.model.user.signup.SignUpRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserService(
    @Autowired private val userRepository: UserRepository,
) {

    @Transactional
    internal fun signUp(
        request: SignUpRequest
    ) {

        userRepository.findByEmail(request.email)?.let {
            throw UserExistException()
        }


        val user = User(
            email = request.email,
            username = request.username,
            profileUrl = request.profileUrl,
        )

        userRepository.save(user)
    }

    @Transactional
    internal fun enterEmail(
        email: String,
    ) {

        userRepository.findByEmail(email)?.let {
            throw UserExistException()
        }

        val user = User(
            email = email,
        )

        userRepository.save(user)
    }

    @Transactional(readOnly = true)
    internal fun checkEmailSignedUp(
        email: String,
    ): Boolean {

        return userRepository.findByEmail(email) != null
    }
}
