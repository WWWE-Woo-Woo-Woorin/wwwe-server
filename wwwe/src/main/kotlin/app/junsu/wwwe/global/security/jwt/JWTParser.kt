package app.junsu.wwwe.global.security.jwt

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jws
import io.jsonwebtoken.Jwts
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Component
import java.util.*

@Component
class JWTParser(
    @Autowired private val userDetailsService: UserDetailsService,
) {

    private var secretKey = "what?"

    private val tokenValidTime = 30 * 60 * 1000L

    init {
        secretKey = Base64.getEncoder().encodeToString(secretKey.toByteArray())
    }

    private fun getClaims(
        token: String,
    ): Jws<Claims> {
        return try {
            Jwts.parser()
                .setSigningKey(secretKey)
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
