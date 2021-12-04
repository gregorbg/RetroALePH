plugins {
    kotlin("jvm") version "1.6.0"
    kotlin("plugin.serialization") version "1.6.0"
}

group = "de.uzk.oas.japan"
version = "0.1-ALPHA"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("reflect"))

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.1")
    implementation("io.github.pdvrieze.xmlutil:serialization-jvm:0.83.0")
    implementation("org.marc4j:marc4j:2.9.2")
    implementation("io.ktor:ktor-client-java:1.6.5")
}
