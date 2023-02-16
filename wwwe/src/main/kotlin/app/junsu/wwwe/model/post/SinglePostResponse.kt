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

internal fun List<Post>.toResponse(): List<PostResponse> {
    return this.map {
        PostResponse(
            postId = it.id!!,
            writer = it.user.name!!,
            content = it.content,
            postImageUrl = it.postImageUrl,
            createdAt = it.createdAt!!,
        )
    }
}
