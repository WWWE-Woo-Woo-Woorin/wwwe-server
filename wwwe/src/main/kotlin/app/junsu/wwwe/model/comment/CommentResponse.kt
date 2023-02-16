package app.junsu.wwwe.model.comment

internal data class CommentResponse(
    val postId: Long,
    val commentId: Long,
    val writer: String,
    val content: String,
    val createdAt: String,
)
