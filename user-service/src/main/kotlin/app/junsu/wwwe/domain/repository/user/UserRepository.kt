package app.junsu.wwwe.domain.repository.user

import app.junsu.wwwe.domain.entity.user.User
import org.springframework.data.repository.CrudRepository

interface UserRepository : CrudRepository<User, Long> {

    fun findByEmail(email: String): User?
}
