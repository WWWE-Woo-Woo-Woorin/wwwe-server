package app.junsu.wwwe.global.security.jwt

import app.junsu.wwwe.model.user.token.TokenResponse
import io.jsonwebtoken.Header
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Component
import java.time.LocalDateTime
import java.util.*

@Component
class JWTProvider(
    @Autowired private val userDetailsService: UserDetailsService,
) {

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

    fun getAuthentication(token: String): Authentication {
        val userDetails = userDetailsService.loadUserByUsername(token)
        return UsernamePasswordAuthenticationToken(
            userDetails,
            "",
            userDetails.authorities,
        )
    }

    fun getUsername(token: String): String? {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).body.subject
    }
}
