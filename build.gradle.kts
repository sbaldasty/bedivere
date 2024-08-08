plugins {
    kotlin("jvm") version "2.0.10"
    kotlin("plugin.serialization") version "2.0.10"
    antlr
}

group = "com.bitflippin"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.7.1")
    antlr("org.antlr:antlr4:4.13.2")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(21)
}

tasks.generateGrammarSource {
    val pkg = "com.bitflippin.bedivere.antlr"
    arguments = arguments + listOf("-package", pkg)
    outputDirectory = outputDirectory.resolve(pkg.split(".").joinToString("/"))
}

sourceSets {
    main {
        java {
            srcDir(tasks.generateGrammarSource)
        }
    }
}