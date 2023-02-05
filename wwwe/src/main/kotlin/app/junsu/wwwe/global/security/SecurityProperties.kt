package app.junsu.wwwe.global.security

import org.springframework.boot.context.properties.ConfigurationProperties
import java.util.*

@ConfigurationProperties(prefix = "jwt")
data class SecurityProperties(
    val tokenValidTime: Long = 30 * 60 * 1000L,
    var secretKey: String = "what?",
) {
    init {
        secretKey = Base64.getEncoder().encodeToString(secretKey.toByteArray())
    }
}
