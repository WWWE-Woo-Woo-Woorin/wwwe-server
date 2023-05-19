package app.junsu.wwwe.domain.entity.post

import app.junsu.wwwe.domain.entity.base.BaseTimeEntity
import app.junsu.wwwe.domain.entity.user.User
import jakarta.persistence.*

@Entity
@Table(name = "tbl_post")
class Post(

    @Id @GeneratedValue(
        strategy = GenerationType.IDENTITY,
    ) val id: Long? = null,

    @ManyToOne(
        fetch = FetchType.EAGER,
    ) val userId: User,

    @Column(
        name = "total_likes",
    ) val totalLikes: Long? = null,

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
    ) val postType: PostType,
) : BaseTimeEntity()
