package br.com.blz.tstjava

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(scanBasePackageClasses = [TestJavaApplication::class])
open class TestJavaApplication

fun main(args: Array<String>) {
  runApplication<TestJavaApplication>(*args)
}
