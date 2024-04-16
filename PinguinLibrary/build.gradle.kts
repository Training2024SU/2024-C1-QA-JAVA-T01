plugins {
    id("java")
}

group = "co.com.sofka"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("com.mysql:mysql-connector-j:8.2.0")
    implementation("org.apache.logging.log4j:log4j-core:2.17.1")
    implementation("org.apache.commons:commons-csv:1.10.0")
}

tasks.test {
    useJUnitPlatform()
}