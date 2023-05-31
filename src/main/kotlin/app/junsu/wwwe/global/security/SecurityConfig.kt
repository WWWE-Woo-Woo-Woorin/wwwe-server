package app.junsu.wwwe.global.security

import app.junsu.wwwe.global.security.filter.FilterConfig
import app.junsu.wwwe.global.security.jwt.JWTParser
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain

@Configuration
class SecurityConfig(
    @Autowired private val jwtParser: JWTParser,
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

                // users
                .requestMatchers(HttpMethod.POST, "/v1/users/signup").permitAll()
                .requestMatchers(HttpMethod.POST, "/v1/users/signup/email").permitAll()
                .requestMatchers(HttpMethod.POST, "/v1/users/signin").permitAll()
                .requestMatchers(HttpMethod.GET, "/v1/users/check/email").permitAll()
                .requestMatchers(HttpMethod.PUT, "/v1/users/token").permitAll()

                // posts
                .requestMatchers(HttpMethod.GET, "/v1/posts").permitAll()

                .anyRequest().authenticated()
            apply(
                FilterConfig(
                    jwtParser,
                )
            )
            build()
        }
    }
}
