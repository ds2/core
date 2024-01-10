plugins {
    java
    kotlin("jvm")
}

description = "DS/2 OSS Core API"

val logbackVersion: String by project

dependencies {
    implementation(platform("ds2.bom:jee-common:2.1.0-alpha.2"))
    implementation("org.projectlombok:lombok")
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.9.22")
    implementation("javax.xml.bind:jaxb-api:2.3.1")
    compileOnly("jakarta.platform", "jakarta.jakartaee-api", "8.0.0")
    testImplementation("jakarta.platform", "jakarta.jakartaee-api", "8.0.0")
    testImplementation("org.testng:testng")
    testImplementation("org.slf4j:jul-to-slf4j")
    testRuntimeOnly("ch.qos.logback", "logback-classic", logbackVersion)
}

tasks.test {
    useTestNG()
}

kotlin {
    jvmToolchain {
        (this as JavaToolchainSpec).languageVersion.set(JavaLanguageVersion.of("17"))
    }
}
