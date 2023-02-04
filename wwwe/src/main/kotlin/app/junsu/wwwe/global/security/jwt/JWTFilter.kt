package app.junsu.wwwe.global.security.jwt

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter

class JWTFilter(
    private val jwtParser: JWTProvider
) : OncePerRequestFilter() {

    private fun getResolvedToken(
        request: HttpServletRequest,
    ): String? {

        val bearerToken = request.getHeader(JWTComponent.HEADER)

        return if (bearerToken != null && bearerToken.startsWith(JWTComponent.PREFIX)) {
            bearerToken.split(" ")[1]
        } else {
            null
        }
    }

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain,
    ) {
        val token = getResolvedToken(request)

        if (token != null) {
            SecurityContextHolder.getContext().authentication = jwtParser.getAuthentication(token)
        }

        filterChain.doFilter(
            request,
            response,
        )
    }
}