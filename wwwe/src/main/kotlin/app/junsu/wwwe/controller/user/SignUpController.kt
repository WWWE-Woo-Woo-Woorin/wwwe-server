package app.junsu.wwwe.controller.user

import app.junsu.wwwe.model.user.enter.EnterUsernameRequest
import app.junsu.wwwe.model.user.signup.SignUpRequest
import app.junsu.wwwe.service.user.UserService
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/users/signup")
private class SignUpController(
    @Autowired private val userService: UserService,
) {

    private val logger = KotlinLogging.logger {}

    @PostMapping
    private fun signUp(
        @RequestBody request: SignUpRequest,
    ) {
        logger.error { request }
        userService.signUp(
            request = request,
        )
    }

    @PostMapping("/email")
    private fun signUpEmail(
        @RequestParam("email") email: String,
    ) {
        userService.signUpEmail(
            email = email,
        )
    }

    @PatchMapping("/username")
    private fun enterUsername(
        @RequestBody request: EnterUsernameRequest,
    ) {
        userService.enterUsername(
            email = request.email,
            username = request.username,
        )
    }
}
