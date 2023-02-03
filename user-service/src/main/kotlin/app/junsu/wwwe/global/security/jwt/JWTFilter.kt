package app.junsu.wwwe.global.security.jwt

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter

class JWTFilter(
    @Autowired private val jwtProvider: JWTProvider,
) : OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain,
    ) {

        val token: String? = getResolvedToken(request)

        token?.run {
            SecurityContextHolder.getContext().authentication = jwtProvider.getAuthentication(token)
        }

        filterChain.doFilter(request, response)
    }

    private fun getResolvedToken(
        request: HttpServletRequest,
    ): String? {

        val token = request.getHeader(JWTProperties.HEADER)

        return if (token != null && token.startsWith(
                JWTProperties.PREFIX,
            )
        ) {
            token.substring(7)
        } else {
            null
        }
    }
}
