plugins {
    java
}

group = "com.github.carrotbyte"
version = "1.0-SNAPSHOT"

repositories {
    mavenLocal()
    mavenCentral()
}

dependencies {
    // RP dependencies
    implementation("com.epam.reportportal:commons-model:5.12.0")
    implementation("com.epam.reportportal:client-java:5.2.14")
    // Serenity dependencies
    implementation("net.serenity-bdd:serenity-core:4.2.1")
    testImplementation(platform("org.junit:junit-bom:5.10.3"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

tasks.named<Test>("test") {
    useJUnitPlatform()
    maxHeapSize = "1G"
    testLogging {
        events("passed")
    }
}
