package app.junsu.wwwe.global.security.filter

import app.junsu.wwwe.global.security.jwt.JWTFilter
import app.junsu.wwwe.global.security.jwt.JWTParser
import org.springframework.security.config.annotation.SecurityConfigurerAdapter
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.DefaultSecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

class FilterConfig(
    private val jwtParser: JWTParser,
) : SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity>() {
    override fun configure(
        builder: HttpSecurity
    ) {
        builder.addFilterBefore(
            JWTFilter(jwtParser),
            UsernamePasswordAuthenticationFilter::class.java,
        ) // TODO exceptionHandlerFilter
    }
}
