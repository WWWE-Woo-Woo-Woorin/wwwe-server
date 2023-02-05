package app.junsu.wwwe.global.security

import app.junsu.wwwe.global.security.filter.FilterConfig
import app.junsu.wwwe.global.security.jwt.JWTParser
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain

@Configuration
class SecurityConfig(
    private val objectMapper: ObjectMapper,
    private val jwtParser: JWTParser,
) {

    @Bean
    fun securityFilterChain(
        http: HttpSecurity,
    ): SecurityFilterChain {
        return http.run {

            cors().and()
            csrf().disable()
            formLogin().disable()

            sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)

            authorizeHttpRequests()
                .requestMatchers(HttpMethod.POST, "/user/signup").permitAll()
                .requestMatchers(HttpMethod.POST, "/user/signin").permitAll()
                .requestMatchers(HttpMethod.PUT, "/user/token").permitAll()

                .anyRequest().authenticated()

            apply(
                FilterConfig(
                    objectMapper,
                    jwtParser,
                )
            )

            build()
        }
    }
}
