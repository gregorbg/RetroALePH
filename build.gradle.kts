plugins {
    kotlin("jvm") version "1.3.72"
    kotlin("plugin.serialization") version "1.3.72"
}

group = "de.uzk.oas.japan"
version = "0.1-ALPHA"

repositories {
    mavenCentral()
    jcenter()
}

dependencies {
    implementation(kotlin("stdlib"))

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-runtime:0.20.0")
    implementation("net.devrieze:xmlutil-serialization-jvm:0.20.0.1")
    implementation("org.marc4j:marc4j:2.9.1")

    runtimeOnly("com.fasterxml.woodstox:woodstox-core:5.0.3")
}
