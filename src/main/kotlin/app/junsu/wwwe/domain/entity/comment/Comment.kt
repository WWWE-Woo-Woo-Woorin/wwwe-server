package app.junsu.wwwe.domain.entity.comment

import app.junsu.wwwe.domain.entity.base.BaseTimeEntity
import app.junsu.wwwe.domain.entity.post.Post
import app.junsu.wwwe.domain.entity.user.User
import jakarta.persistence.*

@Entity
@Table(name = "tbl_comment")
class Comment(

    @Id @GeneratedValue(
        strategy = GenerationType.IDENTITY,
    ) val id: Long? = null,

    @ManyToOne(
        fetch = FetchType.EAGER,
    ) val postId: Post,

    @ManyToOne(
        fetch = FetchType.EAGER,
    ) val userId: User,

    @Column(
        name = "content",
        length = 512,
    ) val content: String,
) : BaseTimeEntity()
