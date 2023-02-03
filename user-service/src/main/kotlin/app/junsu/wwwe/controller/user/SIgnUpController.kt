package app.junsu.wwwe.controller.user

import app.junsu.wwwe.model.user.signup.SignUpRequest
import app.junsu.wwwe.service.user.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/user/signup")
class SIgnUpController(
    @Autowired private val userService: UserService,
) {

    @PostMapping
    fun signUp(
        @RequestBody request: SignUpRequest,
    ) {
        userService.signUp(
            request = request,
        )
    }

    @PostMapping("/email")
    fun signUpEmail(
        @RequestParam("email") email: String,
    ) {
        userService.signUpEmail(
            email = email,
        )
    }

    @PatchMapping("/username")
    fun enterUsername(
        @RequestParam("email") email: String,
        @RequestParam("username") username: String,
    ) {
        userService.enterUsername(
            email = email,
            username = username,
        )
    }
}
