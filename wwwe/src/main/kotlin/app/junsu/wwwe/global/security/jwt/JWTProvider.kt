package app.junsu.wwwe.global.security.jwt

import app.junsu.wwwe.global.security.SecurityProperties
import app.junsu.wwwe.model.user.auth.TokenResponse
import io.jsonwebtoken.Header
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.stereotype.Component
import java.time.LocalDateTime
import java.util.*

@Component
class JWTProvider(
    private val securityProperties: SecurityProperties,
) {

    private fun createAccessToken(
        email: String,
    ): String {
        return Jwts.builder().signWith(
            SignatureAlgorithm.HS256, securityProperties.secretKey,
        ).setSubject(
            email,
        ).setHeaderParam(
            Header.JWT_TYPE, JWTComponent.ACCESS,
        ).setIssuedAt(
            Date(),
        ).setExpiration(
            Date(
                System.currentTimeMillis() + securityProperties.tokenValidTime,
            ),
        ).compact()
    }

    fun getToken(email: String): TokenResponse {
        return TokenResponse(
            accessToken = createAccessToken(email),
            accessTokenExp = LocalDateTime.now().plusSeconds(securityProperties.tokenValidTime / 1000)
        )
    }
}
