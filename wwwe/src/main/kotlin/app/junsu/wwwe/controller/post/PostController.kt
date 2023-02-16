package app.junsu.wwwe.controller.post

import app.junsu.wwwe.domain.entity.post.Post
import app.junsu.wwwe.domain.entity.post.PostType
import app.junsu.wwwe.model.post.CreatePostRequest
import app.junsu.wwwe.service.post.PostService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/posts")
private class PostController(
    @Autowired private val postService: PostService,
) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    private fun createPost(
        @RequestBody request: CreatePostRequest,
    ) {
        postService.createPost(request)
    }

    @GetMapping
    private fun inquirePosts(
        @RequestParam postsType: PostType,
    ): List<Post> {
        return postService.inquirePosts(
            postType = postsType,
        )
    }
}
