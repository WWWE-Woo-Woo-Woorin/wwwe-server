package app.junsu.wwwe.exception

sealed class ServerException(
    val code: Int,
    override val message: String,
) : RuntimeException(message) {

    data class NotFoundException(
        override val message: String,
    ) : ServerException(404, message)

    data class UnAuthorizedException(
        override val message: String = "Invalidate Authorization",
    ) : ServerException(404, message)
}
