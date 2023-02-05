package app.junsu.wwwe.model.user.token

data class TokenRequest(
    val email: String,
    val deviceToken: String,
)
