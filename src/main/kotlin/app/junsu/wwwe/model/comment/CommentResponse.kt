package app.junsu.wwwe.model.comment

import app.junsu.wwwe.domain.entity.comment.Comment
import java.time.LocalDateTime

data class CommentResponse(
    val postId: Long,
    val commentId: Long,
    val writer: String,
    val content: String,
    val createdAt: LocalDateTime,
)

internal fun Comment?.toResponse(): CommentResponse {

    require(this != null)

    return CommentResponse(
        postId = this.postId.id!!,
        commentId = this.id!!,
        writer = this.userId.name!!,
        content = this.content,
        createdAt = this.createdAt!!,
    )
}

internal fun Iterable<Comment?>.toResponse(): Iterable<CommentResponse> {
    return this.map {
        it.toResponse()
    }
}
