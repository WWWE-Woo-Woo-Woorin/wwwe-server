package app.junsu.wwwe.domain.entity.user

import app.junsu.wwwe.domain.entity.base.BaseTimeEntity
import jakarta.persistence.*
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

@Entity
@Table(name = "tbl_user")
class User(

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
    ) var name: String? = null,

    @Column(
        name = "profile_url",
        nullable = true,
        length = 1024,
    ) var profileUrl: String? = null,

    @Column(
        name = "device_token",
        nullable = true,
    ) var deviceToken: String? = null,
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

    fun saveDeviceToken(
        token: String?,
    ) {
        this.deviceToken = token
    }
}
