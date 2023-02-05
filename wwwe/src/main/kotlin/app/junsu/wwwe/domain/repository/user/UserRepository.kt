package app.junsu.wwwe.domain.repository.user

import app.junsu.wwwe.domain.entity.user.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long> {

    fun findByEmail(email: String): User?
}
