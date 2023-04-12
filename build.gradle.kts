plugins {
    kotlin("jvm") version "1.8.20"
    kotlin("plugin.serialization") version "1.8.20"
    id("com.github.ben-manes.versions") version "0.46.0"
}

group = "de.uzk.oas.japan"
version = "0.1-ALPHA"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("reflect"))

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.0")
    implementation("io.github.pdvrieze.xmlutil:serialization-jvm:0.85.0")
    implementation("org.marc4j:marc4j:2.9.2")
    implementation("io.ktor:ktor-client-java:2.2.4")
    implementation("io.ktor:ktor-client-content-negotiation:2.2.4")
    implementation("io.ktor:ktor-serialization-kotlinx-json:2.2.4")
    implementation("org.apache.commons:commons-text:1.10.0")
    implementation("org.apache.commons:commons-lang3:3.12.0")
    implementation("com.andree-surya:moji4j:1.2.0")
}
