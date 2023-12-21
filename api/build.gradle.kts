plugins {
    java
    kotlin("jvm")
}

description = "DS/2 OSS Core API"

val logbackVersion: String by project

dependencies {
    //compileOnly(group: 'javax.enterprise', name: 'cdi-api', version:'1.2')
    //compileOnly(group: 'javax.validation', name: 'validation-api', version:'1.1.0.Final')
    annotationProcessor("org.projectlombok:lombok:1.18.30")
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.9.22")
    compileOnly("org.projectlombok:lombok:1.18.30")
    implementation("javax.xml.bind:jaxb-api:2.3.1")
    compileOnly("jakarta.platform", "jakarta.jakartaee-api", "8.0.0")
    testImplementation("jakarta.platform", "jakarta.jakartaee-api", "8.0.0")
    testImplementation("org.testng:testng:7.4.0")
    testImplementation("org.slf4j:jul-to-slf4j:1.7.36")
    testRuntimeOnly("ch.qos.logback", "logback-classic", logbackVersion)
}

tasks.test {
    useTestNG()
}

kotlin {
    jvmToolchain {
        (this as JavaToolchainSpec).languageVersion.set(JavaLanguageVersion.of("8")) // "8"
    }
}
