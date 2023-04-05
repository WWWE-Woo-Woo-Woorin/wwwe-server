package app.junsu.wwwe.controller.user

import app.junsu.wwwe.model.user.common.TokenResponse
import app.junsu.wwwe.model.user.signin.SignInRequest
import app.junsu.wwwe.service.user.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/users/signin")
class SignInController(
    @Autowired private val userService: UserService,
) {

    @PostMapping
    private fun signIn(
        @RequestBody request: SignInRequest,
    ): TokenResponse {
        return userService.signIn(request)
    }
}
