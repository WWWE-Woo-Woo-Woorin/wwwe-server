package app.junsu.wwwe.controller.post

import app.junsu.wwwe.domain.entity.post.PostType
import app.junsu.wwwe.model.post.CreatePostRequest
import app.junsu.wwwe.model.post.PostResponse
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
    ) : PostResponse{
       return postService.createPost(request)
    }

    @GetMapping
    private fun inquireAllPosts(): List<PostResponse> {
        return postService.inquireAllPosts()
    }

    @GetMapping
    private fun inquirePosts(
        @RequestParam postsType: PostType,
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



    @DeleteMapping("/{post-id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private fun deletePost(
        @PathVariable("post-id") postId: Long,
    ) {
        postService.deletePost(
            postId = postId,
        )
    }
}
