package app.junsu.wwwe.global.security.jwt

import app.junsu.wwwe.model.user.token.TokenResponse
import io.jsonwebtoken.Header
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.stereotype.Component
import java.time.LocalDateTime
import java.util.*

@Component
class JWTProvider {

    private val secret = "wwwe" // todo

    private val secretKey = Base64.getEncoder().encodeToString(secret.toByteArray())

    private fun createAccessToken(
        email: String,
    ): String {
        return Jwts.builder().setHeaderParam(
            Header.JWT_TYPE,
            JWTProperties.ACCESS,
        ).setSubject(email).setIssuedAt(Date()).signWith(
            SignatureAlgorithm.ES256,
            secretKey,
        ).setExpiration(
            Date(
                System.currentTimeMillis() + JWTProperties.tokenValidateTime,
            ),
        ).compact()
    }

    fun getToken(email: String): TokenResponse {
        return TokenResponse(
            accessToken = createAccessToken(email),
            accessTokenExp = LocalDateTime.now().plusSeconds(JWTProperties.tokenValidateTime / 1000),
        )
    }
}
