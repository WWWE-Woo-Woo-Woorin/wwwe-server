package app.junsu.wwwe.service.comment

import app.junsu.wwwe.domain.entity.comment.Comment
import app.junsu.wwwe.domain.repository.comment.CommentRepository
import app.junsu.wwwe.domain.repository.post.PostRepository
import app.junsu.wwwe.exception.ServerException.PostNotFoundException
import app.junsu.wwwe.global.security.SecurityFacade
import app.junsu.wwwe.model.comment.CreateCommentRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CommentService constructor(
    @Autowired private val postRepository: PostRepository,
    @Autowired private val commentRepository: CommentRepository,
    @Autowired private val securityFacade: SecurityFacade,
) {

    @Transactional
    fun createComment(
        postId: Long,
        request: CreateCommentRequest,
    ) {

        val post = postRepository.findPostById(postId) ?: throw PostNotFoundException()

        val user = securityFacade.getCurrentUser()

        val comment = Comment(
            content = request.content,
            post = post,
            user = user,
        )

        commentRepository.save(comment)
    }
}
