package app.junsu.wwwe.controller.user

import app.junsu.wwwe.model.user.common.TokenResponse
import app.junsu.wwwe.model.user.token.TokenRequest
import app.junsu.wwwe.service.user.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/users/token")
private class TokenController(
    @Autowired private val userService: UserService,
) {

    @PutMapping
    private fun regenerateToken(
        @RequestBody request: TokenRequest,
    ): TokenResponse {
        return userService.regenerateToken(request)
    }
}
