package app.junsu.wwwe.controller.user

import app.junsu.wwwe.model.user.signup.SignUpRequest
import app.junsu.wwwe.service.user.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/user")
class UserController(
    @Autowired private val userService: UserService,
) {

    @PostMapping("/signup")
    fun signUp(
        @RequestBody request: SignUpRequest,
    ) {
        return userService.signUp(
            request = request,
        )
    }
}
