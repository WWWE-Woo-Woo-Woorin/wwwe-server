package app.junsu.wwwe.domain.model.user.signup

data class SignUpRequest(
    val email: String,
    val username: String,
    val profileUrl: String? = null,
)
