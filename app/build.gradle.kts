plugins {
    application
    java
    `maven-publish`
}

repositories {
    mavenCentral()
}

publishing {
    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/exoad/cli-java")
            credentials {
                username = System.getenv("GITHUB_ACTOR")
                password = System.getenv("GITHUB_TOKEN")
            }
        }
    }
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter:5.7.2")
    implementation("com.google.guava:guava:30.1.1-jre")
}

application {
    mainClass.set("cli.java.CommandLineJava")
}

tasks.named<Test>("test") {
    useJUnitPlatform()
}
