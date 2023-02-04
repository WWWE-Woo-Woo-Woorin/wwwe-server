package app.junsu.wwwe.model.user.auth

data class SignInRequest(
    val email: String,
    val deviceToken: String,
)
