package app.junsu.wwwe.exception

import com.auth0.jwt.exceptions.TokenExpiredException
import mu.KotlinLogging
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
private class GlobalExceptionHandler {

    private val logger = KotlinLogging.logger {}

    @ExceptionHandler(ServerException::class)
    private fun handleServerException(e: ServerException): ErrorResponse {
        return with(e) {
            logger.error { message }
            ErrorResponse(
                code = code,
                message = message,
            )
        }
    }

    @ExceptionHandler(TokenExpiredException::class)
    private fun handleTokenExpiredException(e: TokenExpiredException): ErrorResponse {
        return with(e) {
            logger.error { message }
            ErrorResponse(
                code = 401,
                message = "Token expired",
            )
        }
    }

    @ExceptionHandler
    private fun handleException(e: Exception): ErrorResponse {
        return with(e) {
            logger.error { e.message }
            ErrorResponse(
                code = 500,
                message = "Internal server error",
            )
        }
    }
}
