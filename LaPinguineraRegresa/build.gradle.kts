plugins {
    id("java")
}

group = "co.com.sofkau"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    implementation ("com.mysql:mysql-connector-j:8.0.32")
    implementation ("com.github.javafaker:javafaker:1.0.2")
    //implementation("org.apache.poi:poi:5.2.5")
}

tasks.test {
    useJUnitPlatform()
}