package app.junsu.wwwe.global.security

import org.springframework.boot.context.properties.ConfigurationProperties
import java.util.Base64

@ConfigurationProperties(prefix = "jwt")
data class SecurityProperties(
    val accessTokenValidTime: Long = 24 * 60 * 60 * 1000L,
    val refreshTokenValidTime: Long = 14 * 24 * 60 * 60 * 1000L,
    var secretKey: String = "what?",
) {
    init {
        secretKey = Base64.getEncoder().encodeToString(secretKey.toByteArray())
    }
}
