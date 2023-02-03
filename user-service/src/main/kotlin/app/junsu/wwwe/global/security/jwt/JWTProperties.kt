package app.junsu.wwwe.global.security.jwt

object JWTProperties {

    const val tokenValidateTime = 30 * 60 * 1000L

    const val HEADER = "Authorization"
    const val PREFIX = "Bearer "
    const val ACCESS = "Access"
}
