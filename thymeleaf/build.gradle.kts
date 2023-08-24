plugins {
    java
}

val logbackVersion: String by project

dependencies {
    implementation(project(":ds2-oss-core-api"))
    implementation("org.thymeleaf:thymeleaf:3.0.11.RELEASE")
    implementation("org.slf4j:slf4j-api:1.7.30")
    compileOnly("jakarta.platform:jakarta.jakartaee-web-api:8.0.0")
    testImplementation("org.testng:testng:6.14.3")
    testImplementation(project(":ds2-oss-core-testutils"))
    testCompileOnly("jakarta.platform:jakarta.jakartaee-web-api:8.0.0")
    testRuntimeOnly("org.jboss.weld.se", "weld-se-core", "2.4.8.Final")
    testRuntimeOnly("org.jboss", "jandex", "1.2.4.Final")
    testRuntimeOnly("ch.qos.logback", "logback-classic", logbackVersion)
    testImplementation(project(":ds2-oss-core-statics"))
    //compileOnly(group: 'javax.enterprise', name: 'cdi-api', version:'1.2')

}

tasks.register("copyResources", Copy::class.java) {
    from("${projectDir}/src/main/resources")
    into("${buildDir}/classes/java/main")
}
tasks.register("copyTestResources", Copy::class.java) {
    from("${projectDir}/src/test/resources")
    into("${buildDir}/classes/java/test")
}
tasks.processResources {
    dependsOn("copyResources")
}
tasks.processTestResources {
    dependsOn("copyTestResources", "copyResources")
}
tasks.register("copyTestStuffToResources", Copy::class.java) {
    from("${buildDir}/classes/java/test")
    into("${buildDir}/resources/test")
}

tasks.test {
    useTestNG()
    maxParallelForks = 3
    dependsOn("copyTestStuffToResources")
}
