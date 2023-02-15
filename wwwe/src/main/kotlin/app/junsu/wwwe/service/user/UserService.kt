package app.junsu.wwwe.service.user

import app.junsu.wwwe.domain.entity.user.User
import app.junsu.wwwe.domain.repository.user.UserRepository
import app.junsu.wwwe.exception.ServerException.*
import app.junsu.wwwe.global.security.SecurityFacade
import app.junsu.wwwe.global.security.jwt.JWTProvider
import app.junsu.wwwe.model.user.common.TokenResponse
import app.junsu.wwwe.model.user.fetch.FetchUserInformationResponse
import app.junsu.wwwe.model.user.signin.SignInRequest
import app.junsu.wwwe.model.user.signup.SignUpRequest
import app.junsu.wwwe.model.user.token.TokenRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.ResponseStatus

@Service
class UserService(
    @Autowired private val userRepository: UserRepository,
    @Autowired private val securityFacade: SecurityFacade,
    @Autowired private val jwtProvider: JWTProvider,
) {

    @Transactional
    internal fun signIn(
        request: SignInRequest,
    ): TokenResponse {

        val user = userRepository.findByEmail(request.email) ?: throw UserNotFoundException()

        return jwtProvider.getToken(request.email).also {
            user.saveDeviceToken(request.deviceToken)
        }
    }

    @Transactional
    internal fun signUp(
        request: SignUpRequest
    ) {

        if (userRepository.findByEmail(request.email) != null) throw UserExistException()


        val user = User(
            email = request.email,
            name = request.username,
            profileUrl = request.profileUrl,
        )

        userRepository.save(user)
    }

    @Transactional
    internal fun regenerateToken(
        request: TokenRequest,
    ): TokenResponse {

        val user = userRepository.findByEmail(request.email) ?: throw UserNotFoundException()

        return jwtProvider.getToken(user.email)
    }

    @Transactional
    @ResponseStatus(HttpStatus.NO_CONTENT)
    internal fun signUpEmail(
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

        if (user.name != null) throw UsernameAlreadyEnteredException()

        user.name = username

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

        return user.name != null
    }

    @Transactional(readOnly = true)
    internal fun fetchUserInformation(): FetchUserInformationResponse {

        val user = securityFacade.getCurrentUser()

        return FetchUserInformationResponse(
            email = user.email,
            username = user.username,
            profileUrl = user.profileUrl,
        )
    }
}
