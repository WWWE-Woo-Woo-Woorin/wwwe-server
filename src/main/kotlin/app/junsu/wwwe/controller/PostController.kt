package app.junsu.wwwe.controller

import app.junsu.wwwe.domain.entity._common.TimeOrder
import app.junsu.wwwe.domain.entity.post.PostType
import app.junsu.wwwe.model.post.CreatePostRequest
import app.junsu.wwwe.model.post.PostResponse
import app.junsu.wwwe.model.post.UpdatePostRequest
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
    ): PostResponse {
        return postService.createPost(request)
    }

    @GetMapping
    private fun inquireAllPosts(
        @RequestParam(
            name = "order",
            required = false,
        ) order: TimeOrder?,
    ): List<PostResponse> {
        return postService.inquireAllPosts(order ?: TimeOrder.LATEST)
    }

    @GetMapping("/type")
    private fun inquirePosts(
        @RequestParam("type") postsType: PostType,
    ): List<PostResponse> {
        return postService.inquirePosts(
            postType = postsType,
        )
    }

    @GetMapping("/{post-id}")
    private fun inquirePost(
        @PathVariable("post-id") postId: Long,
    ): PostResponse {
        return postService.inquirePost(
            postId = postId,
        )
    }

    @PatchMapping("/{post-id}")
    private fun updatePost(
        @PathVariable("post-id") postId: Long,
        @RequestBody request: UpdatePostRequest,
    ): PostResponse {
        return postService.updatePost(
            postId = postId,
            request = request,
        )
    }

    @DeleteMapping("/{post-id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private fun deletePost(
        @PathVariable("post-id") postId: Long,
    ) {
        return postService.deletePost(
            postId = postId,
        )
    }
}
