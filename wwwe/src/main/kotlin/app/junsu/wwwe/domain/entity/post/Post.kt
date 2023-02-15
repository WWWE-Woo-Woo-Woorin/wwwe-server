package app.junsu.wwwe.domain.entity.post

import app.junsu.wwwe.domain.entity.base.BaseTimeEntity
import app.junsu.wwwe.domain.entity.user.User
import jakarta.persistence.*

@Entity
@Table(name = "tbl_post")
data class Post(

    @Id @GeneratedValue(
        strategy = GenerationType.AUTO,
    ) val id: Long? = null,

    @ManyToOne(
        fetch = FetchType.LAZY,
    ) @JoinColumn(
        name = "user_id",
        nullable = false,
    ) val user: User,

    @Column(
        name = "total_likes",
    ) val totalLikes: Long? = null,

    @Column(
        name = "writer",
        length = 8,
    ) val writer: String,

    @Column(
        name = "total_comments",
    ) val totalComments: Long? = null,

    @Column(
        name = "content",
        length = 1024,
    ) val content: String,

    @Column(
        name = "post_image_url",
        length = 1024,
    ) val postImageUrl: String,

    @Enumerated @Column(
        name = "post_type",
    ) val postType: PostType
) : BaseTimeEntity()
