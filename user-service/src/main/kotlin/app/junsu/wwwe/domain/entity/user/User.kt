package app.junsu.wwwe.domain.entity.user

import jakarta.persistence.*

@Entity
@Table(name = "user")
data class User(
    @Id @GeneratedValue(strategy = GenerationType.AUTO) val id: Long? = null,
    @Column(name = "email", nullable = false, length = 64) val email: String,
    @Column(name = "username", nullable = true, length = 8) val username: String? = null,
    @Column(name = "profile_url", nullable = true, length = 1024) val profileUrl: String? = null,
)
