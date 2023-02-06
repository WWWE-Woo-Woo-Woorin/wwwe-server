package app.junsu.wwwe.controller.post

import app.junsu.wwwe.model.post.CreatePostRequest
import app.junsu.wwwe.service.post.PostService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/posts")
class PostController(
    @Autowired private val postService: PostService,
) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createPost(
        @RequestBody request: CreatePostRequest,
    ) {
        postService.createPost(request)
    }
}
