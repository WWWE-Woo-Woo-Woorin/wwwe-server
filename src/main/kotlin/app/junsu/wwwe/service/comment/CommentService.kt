package app.junsu.wwwe.service.comment

import app.junsu.wwwe.domain.entity.comment.Comment
import app.junsu.wwwe.domain.repository.comment.CommentRepository
import app.junsu.wwwe.domain.repository.post.PostRepository
import app.junsu.wwwe.exception.ServerException.CommentNotFoundException
import app.junsu.wwwe.exception.ServerException.PostNotFoundException
import app.junsu.wwwe.global.security.SecurityFacade
import app.junsu.wwwe.model.comment.CommentResponse
import app.junsu.wwwe.model.comment.CreateCommentRequest
import app.junsu.wwwe.model.comment.UpdateCommentRequest
import app.junsu.wwwe.model.comment.toResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.ResponseStatus

@Service
class CommentService constructor(
    @Autowired private val postRepository: PostRepository,
    @Autowired private val commentRepository: CommentRepository,
    @Autowired private val securityFacade: SecurityFacade,
) {

    @Transactional
    internal fun createComment(
        postId: Long,
        request: CreateCommentRequest,
    ): CommentResponse {

        val post = postRepository.findPostById(
            id = postId,
        ) ?: throw PostNotFoundException()

        val user = securityFacade.getCurrentUser()

        val comment = Comment(
            content = request.content,
            postId = post,
            userId = user,
        )

        return commentRepository.save(comment).toResponse()
    }

    @Transactional(readOnly = true)
    internal fun inquireComments(
        postId: Long,
    ): List<CommentResponse> {

        val internalComments = commentRepository.findAllByPostId(
            postId = postId,
        )

        return internalComments.toResponse().toList()
    }

    @Transactional
    internal fun updateComment(
        commentId: Long,
        request: UpdateCommentRequest,
    ): CommentResponse {

        val internalComment = commentRepository.findCommentById(
            commentId = commentId,
        ) ?: throw CommentNotFoundException()

        val newComment = Comment(
            id = internalComment.id!!,
            postId = internalComment.postId,
            userId = internalComment.userId,
            content = request.content,
        )

        return commentRepository.save(newComment).toResponse()
    }

    @Transactional
    @ResponseStatus(HttpStatus.NO_CONTENT)
    internal fun deleteComment(
        commentId: Long,
    ) {
        return postRepository.deleteById(commentId)
    }
}
