package app.junsu.wwwe.global.converter

import app.junsu.wwwe.domain.entity.post.PostType
import app.junsu.wwwe.domain.entity.post.toPost
import org.springframework.core.convert.converter.Converter

internal class PostConverter : Converter<String, PostType> {
    override fun convert(source: String): PostType {
        return source.toPost()
    }
}