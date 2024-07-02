package dev.jombi.template

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringTemplatesApplication

fun main(args: Array<String>) {
    runApplication<SpringTemplatesApplication>(*args)
}
