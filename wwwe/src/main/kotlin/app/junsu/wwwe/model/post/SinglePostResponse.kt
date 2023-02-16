package app.junsu.wwwe.model.post

data class PostResponse(
    val postId: Long,
    val writer: String,
    val content: String,
    val postImageUrl: String,
)
