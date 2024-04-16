plugins {
    id("java")
}

group = "org.moreno.cristian"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("com.mysql:mysql-connector-j:8.3.0")
    implementation("com.github.javafaker:javafaker:1.0.2")
    implementation("org.apache.poi:poi:5.2.5")

}

tasks.test {
    useJUnitPlatform()
}