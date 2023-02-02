package app.junsu.wwwe.service.user

import app.junsu.wwwe.domain.entity.user.User
import app.junsu.wwwe.domain.repository.user.UserRepository
import app.junsu.wwwe.exception.ServerException.*
import app.junsu.wwwe.model.user.signup.SignUpRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.ResponseStatus

@Service
class UserService(
    @Autowired private val userRepository: UserRepository,
) {

    @Transactional
    internal fun signUp(
        request: SignUpRequest
    ) {

        if (userRepository.findByEmail(request.email) != null) throw UserExistException()


        val user = User(
            email = request.email,
            username = request.username,
            profileUrl = request.profileUrl,
        )

        userRepository.save(user)
    }

    @Transactional
    @ResponseStatus(HttpStatus.NO_CONTENT)
    internal fun enterEmail(
        email: String,
    ) {

        if (userRepository.findByEmail(email) != null) throw UserExistException()

        val user = User(
            email = email,
        )

        userRepository.save(user)
    }

    @Transactional
    @ResponseStatus(HttpStatus.NO_CONTENT)
    internal fun enterUsername(
        email: String,
        username: String,
    ) {

        val user = userRepository.findByEmail(email) ?: throw UserNotFoundException()

        if (user.username != null) throw UsernameAlreadyEnteredException()

        user.username = username

        userRepository.save(user)
    }

    @Transactional(readOnly = true)
    internal fun checkEmailSignedUp(
        email: String,
    ): Boolean = userRepository.findByEmail(email) != null

    @Transactional(readOnly = true)
    internal fun checkUsernameEntered(
        email: String,
    ): Boolean {

        val user = userRepository.findByEmail(email) ?: throw UserNotFoundException()

        return user.username != null
    }
}
