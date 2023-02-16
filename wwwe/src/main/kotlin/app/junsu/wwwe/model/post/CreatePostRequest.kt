package app.junsu.wwwe.model.post

data class CreatePostRequest(
    val postImageUrl: String,
    val content: String,
    val postType: String,
)
