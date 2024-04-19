plugins {
    id("java")
}

group = "co.com.training"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    // https://mvnrepository.com/artifact/com.mysql/mysql-connector-j
    implementation ("com.mysql:mysql-connector-j:8.0.32")
    implementation("com.github.javafaker:javafaker:1.0.2")

}

tasks.test {
    useJUnitPlatform()
}