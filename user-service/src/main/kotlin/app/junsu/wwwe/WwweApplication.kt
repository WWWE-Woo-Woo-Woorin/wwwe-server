package app.junsu.wwwe

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@SpringBootApplication
@EnableJpaAuditing
class WwweApplication

fun main(args: Array<String>) {
    runApplication<WwweApplication>(*args)
}
