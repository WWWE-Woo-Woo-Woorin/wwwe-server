package app.junsu.wwwe.model.post

import app.junsu.wwwe.domain.entity.post.Post
import java.time.LocalDateTime

internal data class PostResponse(
    val postId: Long,
    val writer: String,
    val content: String,
    val postImageUrl: String,
    val createdAt: LocalDateTime,
)

internal fun Post.toResponse(): PostResponse {
    return PostResponse(
        postId = this.id!!,
        writer = this.user.name!!,
        content = this.content,
        postImageUrl = this.postImageUrl,
        createdAt = this.createdAt!!,
    )
}

internal fun Iterable<Post>.toResponse(): Iterable<PostResponse> {
    return this.map {
        it.toResponse()
    }
}
