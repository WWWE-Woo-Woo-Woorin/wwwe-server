package app.junsu.wwwe.service.post

import app.junsu.wwwe.domain.entity.post.Post
import app.junsu.wwwe.domain.repository.post.PostRepository
import app.junsu.wwwe.domain.repository.user.UserRepository
import app.junsu.wwwe.global.security.SecurityFacade
import app.junsu.wwwe.model.post.CreatePostRequest
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
    ): Post {

        val user = securityFacade.getCurrentUser()

        val post = Post(
            user = user,
            writer = user.name!!,
            content = request.content,
            postImageUrl = request.postImageUrl,
        )

        return postRepository.save(post)
    }
}