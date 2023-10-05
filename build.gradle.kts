plugins {
    id("java")
}

group = "klaxon.klaxon"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven {
        url = uri("https://maven.jzy3d.org/releases/")
    }
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    implementation("org.jzy3d:jzy3d-core:2.2.1")
    implementation("org.jzy3d:jzy3d-native-jogl-swing:2.2.1")
}

tasks.test {
    useJUnitPlatform()
}