package app.junsu.wwwe.controller.comment

import app.junsu.wwwe.model.comment.CreateCommentRequest
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
        @PathVariable("post-id") parameter: String,
        @RequestBody request: CreateCommentRequest,
    ) {
        commentService.createComment(
            request = request,
        )
    }
}
