plugins {
    id("java")
    id("com.diffplug.spotless") version "6.22.0"
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

spotless {

    java {
        // don't need to set target, it is inferred from java

        // make sure every file has the following copyright header.
        // optionally, Spotless can set copyright years by digging
        // through git history (see "license" section below)
        licenseHeader(
            "/** LazerBeam - a program to calculate beam deflection and possibly a bit more\n" +
            " * Copyright (C) \$YEAR  ah-OOG-ah\n" +
            " * \n" +
            " * This program is free software: you can redistribute it and/or modify\n" +
            " * it under the terms of the GNU General Public License as published by\n" +
            " * the Free Software Foundation, either version 3 of the License, or\n" +
            " * (at your option) any later version.\n" +
            " * \n" +
            " * This program is distributed in the hope that it will be useful,\n" +
            " * but WITHOUT ANY WARRANTY; without even the implied warranty of\n" +
            " * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the\n" +
            " * GNU General Public License for more details.\n" +
            " * \n" +
            " * You should have received a copy of the GNU General Public License\n" +
            " * along with this program.  If not, see <https://www.gnu.org/licenses/>.\n" +
            " */\n"
        )
    }
}

tasks.test {
    useJUnitPlatform()
}