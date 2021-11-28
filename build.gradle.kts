plugins {
    kotlin("jvm") version "1.5.31"
    kotlin("plugin.serialization") version "1.5.31"
}

group = "de.uzk.oas.japan"
version = "0.1-ALPHA"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.1")
    implementation("io.github.pdvrieze.xmlutil:core-jvm:0.83.0")
    implementation("io.github.pdvrieze.xmlutil:serialization-jvm:0.83.0")
    implementation("org.marc4j:marc4j:2.9.2")
}
