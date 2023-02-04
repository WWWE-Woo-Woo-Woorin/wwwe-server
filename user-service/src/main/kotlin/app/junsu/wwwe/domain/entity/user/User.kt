package app.junsu.wwwe.domain.entity.user

import app.junsu.wwwe.domain.entity.base.BaseTimeEntity
import jakarta.persistence.*

@Entity
@Table(name = "tbl_user")
data class User(
    @Id @GeneratedValue(
        strategy = GenerationType.AUTO,
    ) val id: Long? = null,

    @Column(
        name = "email",
        nullable = false,
        updatable = false,
        unique = true,
        length = 64,
    ) val email: String,

    @Column(
        name = "username",
        nullable = true,
        length = 8,
    ) var username: String? = null,

    @Column(
        name = "profile_url",
        nullable = true,
        length = 1024,
    ) var profileUrl: String? = null,
) : BaseTimeEntity()
