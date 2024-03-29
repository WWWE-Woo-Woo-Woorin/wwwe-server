package app.junsu.wwwe.domain.repository.post

import app.junsu.wwwe.domain.entity.post.Post
import app.junsu.wwwe.domain.entity.post.PostType
import org.springframework.data.jpa.repository.JpaRepository

interface PostRepository : JpaRepository<Post, Long> {

    fun findPostById(id: Long): Post?

    fun findAllByPostType(postType: PostType): List<Post>
}
