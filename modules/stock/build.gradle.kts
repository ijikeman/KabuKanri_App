plugins {
    kotlin("jvm") version "2.2.0"
    kotlin("plugin.jpa") version "2.2.0"
    id("org.springframework.boot") version "3.5.3"
    id("io.spring.dependency-management") version "1.1.7"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // Webアプリケーション、JPAに必要な基本的な依存関係
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    // KotlinでEntityを使う場合に推奨される依存関係
    implementation("org.jetbrains.kotlin:kotlin-reflect")
}

java {
    sourceCompatibility = JavaVersion.VERSION_21 // Java Versionを指定
}
