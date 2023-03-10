package app.junsu.wwwe.controller.user

import app.junsu.wwwe.service.user.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/users/check")
class CheckController(
    @Autowired private val userService: UserService,
) {

    @GetMapping("/email")
    private fun checkEmailSignedUp(
        @RequestParam(name = "email") email: String,
    ): Boolean {
        return userService.checkEmailSignedUp(email)
    }

    @GetMapping("/username")
    private fun checkUsernameEntered(
        @RequestParam(name = "email") email: String,
    ): Boolean {
        return userService.checkUsernameEntered(email)
    }
}
