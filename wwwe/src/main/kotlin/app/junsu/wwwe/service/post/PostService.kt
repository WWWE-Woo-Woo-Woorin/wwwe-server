package app.junsu.wwwe.service.post

import app.junsu.wwwe.domain.entity.post.Post
import app.junsu.wwwe.domain.entity.post.PostType
import app.junsu.wwwe.domain.entity.post.toPost
import app.junsu.wwwe.domain.repository.post.PostRepository
import app.junsu.wwwe.exception.ServerException.PostNotFoundException
import app.junsu.wwwe.global.security.SecurityFacade
import app.junsu.wwwe.model.post.CreatePostRequest
import app.junsu.wwwe.model.post.PostResponse
import app.junsu.wwwe.model.post.toResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PostService(
    @Autowired private val postRepository: PostRepository,
    @Autowired private val securityFacade: SecurityFacade,
) {

    @Transactional
    internal fun createPost(
        request: CreatePostRequest,
    ): PostResponse {

        val user = securityFacade.getCurrentUser()

        val post = Post(
            user = user,
            content = request.content,
            postImageUrl = request.postImageUrl,
            postType = request.postType.toPost(),
        )

        return postRepository.save(post).toResponse()
    }

    @Transactional(readOnly = true)
    internal fun inquirePosts(
        postType: PostType,
    ): List<PostResponse> {

        val internalPosts = postRepository.findAllByPostType(
            postType = postType,
        )

        return internalPosts.toResponse().toList()
    }

    @Transactional(readOnly = true)
    internal fun inquirePost(
        postId: Long,
    ): PostResponse {

        val internalPost = postRepository.findPostById(
            id = postId,
        ) ?: throw PostNotFoundException()

        return internalPost.toResponse()
    }

    @Transactional
    internal fun deletePost(
        postId: Long,
    ) {
        return postRepository.deleteById(postId)
    }
}
