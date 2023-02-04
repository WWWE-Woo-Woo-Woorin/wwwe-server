package app.junsu.wwwe.global.security.jwt

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "jwt")
data class JWTProperties(
    var secret: String,
    val accessTokenExp: Long,
    val refreshTokenExp: Long,
)
