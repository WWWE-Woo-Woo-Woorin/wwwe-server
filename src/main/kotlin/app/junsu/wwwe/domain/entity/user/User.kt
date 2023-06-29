package app.junsu.wwwe.domain.entity.user

import app.junsu.wwwe.domain.entity.base.BaseTimeEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.hibernate.annotations.ColumnDefault
import org.hibernate.annotations.DynamicInsert
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

@DynamicInsert
@Entity
@Table(name = "tbl_user")
class User(

    @Id @GeneratedValue(
        strategy = GenerationType.IDENTITY,
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
    ) var name: String? = null,

    @Column(
        name = "profile_url",
        nullable = false,
        length = 1024,
    ) @ColumnDefault(DEFAULT_PROFILE_IMAGE_URL) var profileUrl: String? = null,
) : BaseTimeEntity(), UserDetails {

    override fun getAuthorities(): MutableCollection<out GrantedAuthority>? {
        return null
    }

    override fun getPassword(): String? {
        return null
    }

    override fun getUsername(): String {
        return email
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }
}

const val DEFAULT_PROFILE_IMAGE_URL =
    "https://i.natgeofe.com/k/b086c650-681b-4222-b163-a2190a7ac262/quokka-chomping-leaf_square.jpg"
