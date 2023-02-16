package app.junsu.wwwe.global.config

import app.junsu.wwwe.global.converter.PostConverter
import org.springframework.context.annotation.Configuration
import org.springframework.format.FormatterRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
private class WebConfig : WebMvcConfigurer {
    override fun addFormatters(
        registry: FormatterRegistry,
    ) {
        registry.addConverter(
            PostConverter(),
        )
    }
}
