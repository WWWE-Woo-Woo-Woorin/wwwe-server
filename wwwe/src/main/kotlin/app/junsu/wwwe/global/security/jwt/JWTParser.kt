package app.junsu.wwwe.global.security.jwt

import app.junsu.wwwe.global.security.SecurityProperties
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jws
import io.jsonwebtoken.Jwts
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Component

@Component
class JWTParser(
    @Autowired private val userDetailsService: UserDetailsService,
    @Autowired private val securityProperties: SecurityProperties,
) {

    private fun getClaims(
        token: String,
    ): Jws<Claims> {
        return try {
            Jwts.parser()
                .setSigningKey(securityProperties.secretKey)
                .parseClaimsJws(token)
        } catch (e: Exception) {
            throw e // Todo implement server exceptions
        }
    }

    fun getAuthentication(
        token: String,
    ): Authentication {

        val claims = getClaims(token)

        val details = userDetailsService.loadUserByUsername(
            claims.body.id,
        )

        return UsernamePasswordAuthenticationToken(
            details,
            "",
            details.authorities,
        )
    }
}
