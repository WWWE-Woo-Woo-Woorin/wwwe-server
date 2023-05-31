package app.junsu.wwwe.exception

import mu.KotlinLogging
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.client.HttpClientErrorException.Unauthorized

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

    @ExceptionHandler(Unauthorized::class)
    private fun handleTokenExpiredException(e: Unauthorized): ErrorResponse {
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
