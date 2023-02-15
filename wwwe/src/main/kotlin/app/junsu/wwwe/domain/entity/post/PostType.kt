package app.junsu.wwwe.domain.entity.post

enum class PostType {
    DEFAULT, MAJOR, CLUB, ;
}

internal fun String.toPost(): PostType {
    return when (this) {
        "DEFAULT" -> PostType.DEFAULT
        "MAJOR" -> PostType.MAJOR
        "CLUB" -> PostType.CLUB
        else -> throw IllegalArgumentException()
    }
}
