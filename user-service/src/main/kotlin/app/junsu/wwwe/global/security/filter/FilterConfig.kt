package app.junsu.wwwe.global.security.filter

import app.junsu.wwwe.global.security.jwt.JWTFilter
import app.junsu.wwwe.global.security.jwt.JWTProvider
import com.fasterxml.jackson.databind.ObjectMapper
import org.hibernate.tool.schema.spi.ExceptionHandler
import org.springframework.security.config.annotation.SecurityConfigurerAdapter
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.DefaultSecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

class FilterConfig(
    private val objectMapper: ObjectMapper,
    private val jwtProvider: JWTProvider,
) : SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity>() {
    override fun configure(
        builder: HttpSecurity
    ) {
        builder.addFilterBefore(
            JWTFilter(jwtProvider),
            UsernamePasswordAuthenticationFilter::class.java,
        ) // TODO exceptionHandlerFilter
    }
}
