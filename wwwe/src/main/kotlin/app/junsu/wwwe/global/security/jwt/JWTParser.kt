package app.junsu.wwwe.global.security.jwt

import app.junsu.wwwe.global.security.SecurityProperties
import app.junsu.wwwe.global.security.auth.AuthDetailsService
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component

@Component
class JWTParser(
    @Autowired private val authDetailsService: AuthDetailsService,
    @Autowired private val securityProperties: SecurityProperties,
) {

    private fun getClaims(
        token: String,
    ): Claims {
        return try {
            Jwts.parser().setSigningKey(securityProperties.secretKey).parseClaimsJws(token).body
        } catch (e: Exception) {
            throw e // Todo implement server exceptions
        }
    }

    fun getAuthentication(
        token: String,
    ): Authentication {

        val claims = getClaims(token)

        val email = claims.subject

        val details = authDetailsService.loadUserByUsername(email)

        return UsernamePasswordAuthenticationToken(
            details,
            "",
            details.authorities,
        )
    }
}
