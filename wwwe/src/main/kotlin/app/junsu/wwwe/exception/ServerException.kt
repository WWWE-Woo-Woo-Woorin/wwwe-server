package app.junsu.wwwe.exception

sealed class ServerException(
    val code: Int,
    override val message: String,
) : RuntimeException(message) {

    data class UnauthorizedException(
        override val message: String = "JWT Not Authorized",
    ) : ServerException(
        code = 401,
        message = message,
    )

    data class UserNotFoundException(
        override val message: String = "User Does Not Exists",
    ) : ServerException(
        code = 404,
        message = message,
    )

    data class UserExistException(
        override val message: String = "User Already Exists",
    ) : ServerException(
        code = 409,
        message = message,
    )

    data class UsernameAlreadyEnteredException(
        override val message: String = "Username Already Entered",
    ) : ServerException(
        code = 409,
        message = message,
    )
}
