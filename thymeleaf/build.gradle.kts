plugins {
    java
}

dependencies {
    implementation(project(":ds2-oss-core-api"))
    implementation("org.thymeleaf:thymeleaf:3.0.11.RELEASE")
    implementation("org.slf4j:slf4j-api:1.7.30")
    compileOnly("jakarta.platform:jakarta.jakartaee-web-api:8.0.0")
    testImplementation("org.testng:testng:6.14.3")
    testImplementation(project(":ds2-oss-core-testutils"))
    testCompileOnly("jakarta.platform:jakarta.jakartaee-web-api:8.0.0")
}

tasks.test {
    useTestNG()
    maxParallelForks = 3
}
