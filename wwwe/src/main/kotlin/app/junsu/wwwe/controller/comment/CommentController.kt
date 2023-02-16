package app.junsu.wwwe.controller.comment

import app.junsu.wwwe.model.comment.CommentResponse
import app.junsu.wwwe.model.comment.CreateCommentRequest
import app.junsu.wwwe.model.comment.UpdateCommentRequest
import app.junsu.wwwe.service.comment.CommentService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/posts/{post-id}/comments")
private class CommentController(
    @Autowired private val commentService: CommentService,
) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    private fun createComment(
        @PathVariable("post-id") postId: Long,
        @RequestBody request: CreateCommentRequest,
    ) {
        commentService.createComment(
            postId = postId,
            request = request,
        )
    }

    @GetMapping
    private fun inquireComments(
        @PathVariable("post-id") postId: Long,
    ): List<CommentResponse> {
        return commentService.inquireComments(
            postId = postId,
        )
    }

    @PatchMapping("/{comment-id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private fun updateComment(
        @PathVariable("post-id") postId: Long,
        @PathVariable("comment-id") commentId: Long,
        @RequestBody request: UpdateCommentRequest,
    ) {
        commentService.updateComment(
            commentId = commentId,
            request = request,
        )
    }
}
