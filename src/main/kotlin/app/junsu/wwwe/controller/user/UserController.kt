package app.junsu.wwwe.controller.user

import app.junsu.wwwe.model.user.fetch.FetchUserInformationResponse
import app.junsu.wwwe.service.user.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/users")
private class UserController(
    @Autowired private val userService: UserService,
) {

    @GetMapping
    private fun fetchUserInformation(): FetchUserInformationResponse {
        return userService.fetchUserInformation()
    }
}
