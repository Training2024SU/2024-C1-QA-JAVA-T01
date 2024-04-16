plugins {
    id("java")
}

group = "Garcia.Juan"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    // https://mvnrepository.com/artifact/com.mysql/mysql-connector-j
    implementation("com.mysql:mysql-connector-j:8.0.33")

    // https://mvnrepository.com/artifact/com.github.javafaker/javafaker
    implementation("com.github.javafaker:javafaker:1.0.2")

    implementation("org.apache.poi:poi:5.3.1") // Dependencia principal de Apache POI
    implementation("org.apache.poi:poi-ooxml:5.3.1")
}

tasks.test {
    useJUnitPlatform()
}