package devcoop.hyunjoon.selfcounter

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SelfcounterApplication

fun main(args: Array<String>) {
    runApplication<SelfcounterApplication>(*args)
}
