package app.junsu.wwwe.domain.repository.comment

import app.junsu.wwwe.domain.entity.comment.Comment
import org.springframework.data.jpa.repository.JpaRepository

interface CommentRepository : JpaRepository<Comment, Long> {

    fun findAllByPostId(postId: Long): List<Comment>
}
