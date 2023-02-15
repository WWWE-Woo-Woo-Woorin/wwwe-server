package app.junsu.wwwe.domain.entity.post

enum class PostType {
    DEFAULT, MAJOR, CLUB, ;

    fun String.toPost(): PostType {
        return when (this) {
            "DEFAULT" -> DEFAULT
            "MAJOR" -> MAJOR
            "CLUB" -> CLUB
            else -> throw IllegalArgumentException()
        }
    }
}
