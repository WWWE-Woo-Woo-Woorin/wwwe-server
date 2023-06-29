package app.junsu.wwwe.model.post

import app.junsu.wwwe.domain.entity.post.Post
import app.junsu.wwwe.domain.entity.post.PostType
import java.time.LocalDateTime

internal data class PostResponse(
    val postId: Long,
    val postType: PostType,
    val writer: String,
    val writerProfileImageUrl: String,
    val content: String,
    val postImageUrl: String,
    val createdAt: LocalDateTime,
)

internal fun Post.toResponse(): PostResponse {
    return PostResponse(
        postId = this.id!!,
        postType = this.postType,
        writer = this.user.name!!,
        writerProfileImageUrl = this.user.profileUrl!!,
        content = this.content,
        postImageUrl = this.postImageUrl,
        createdAt = this.createdAt!!,
    )
}

internal fun List<Post>.toResponse(): List<PostResponse> {
    return if (this.isEmpty()) emptyList() else this.map { it.toResponse() }
}
