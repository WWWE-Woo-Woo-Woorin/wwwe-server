package app.junsu.wwwe

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class WwweServerApplication

fun main(args: Array<String>) {
	runApplication<WwweServerApplication>(*args)
}
