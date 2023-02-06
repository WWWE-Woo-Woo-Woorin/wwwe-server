package app.junsu.wwwe.model.user.signin

data class SignInRequest(
    val email: String,
    val deviceToken: String? = null,
)
