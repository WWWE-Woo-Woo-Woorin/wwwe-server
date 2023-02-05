package app.junsu.wwwe.domain.repository.post

import app.junsu.wwwe.domain.entity.post.Post
import org.springframework.data.jpa.repository.JpaRepository

interface PostRepository : JpaRepository<Post, Long>
