package app.junsu.wwwe.controller

import app.junsu.wwwe.model.user.common.TokenResponse
import app.junsu.wwwe.model.user.enter.EnterUsernameRequest
import app.junsu.wwwe.model.user.fetch.FetchUserInformationResponse
import app.junsu.wwwe.model.user.signin.SignInRequest
import app.junsu.wwwe.model.user.signup.SignUpRequest
import app.junsu.wwwe.model.user.token.TokenRequest
import app.junsu.wwwe.service.user.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/users")
class UserController(
    @Autowired private val userService: UserService,
) {

    @GetMapping
    private fun fetchUserInformation(): FetchUserInformationResponse {
        return userService.fetchUserInformation()
    }

    @PostMapping("/signin")
    private fun signIn(
        @RequestBody request: SignInRequest,
    ): TokenResponse {
        return userService.signIn(request)
    }

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    private fun signUp(
        @RequestBody request: SignUpRequest,
    ) {
        userService.signUp(
            request = request,
        )
    }

    @PostMapping("/signup/email")
    private fun signUpEmail(
        @RequestParam(
            name = "email",
        ) email: String,
    ) {
        userService.signUpEmail(
            email = email,
        )
    }

    @PatchMapping("/signup/username")
    private fun enterUsername(
        @RequestBody request: EnterUsernameRequest,
    ) {
        userService.enterUsername(
            email = request.email,
            username = request.username,
        )
    }

    @GetMapping("/check/email")
    private fun checkEmailSignedUp(
        @RequestParam(
            name = "email",
        ) email: String,
    ): Boolean {
        return userService.checkEmailSignedUp(email)
    }

    @GetMapping("/check/username")
    private fun checkUsernameEntered(
        @RequestParam(
            name = "email",
        ) email: String,
    ): Boolean {
        return userService.checkUsernameEntered(email)
    }

    @PutMapping("/token")
    private fun regenerateToken(
        @RequestBody request: TokenRequest,
    ): TokenResponse {
        return userService.regenerateToken(request)
    }
}
