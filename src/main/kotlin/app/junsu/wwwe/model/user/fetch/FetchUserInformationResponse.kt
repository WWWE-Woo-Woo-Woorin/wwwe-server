package app.junsu.wwwe.model.user.fetch

data class FetchUserInformationResponse(
    val email: String,
    val username: String,
    val profileUrl: String? = null,
)
