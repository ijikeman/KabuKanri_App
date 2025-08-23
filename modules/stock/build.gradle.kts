plugins {
    kotlin("jvm")
    kotlin("plugin.jpa") // デフォルトコンストラクタを設定することを回避する
    kotlin("plugin.spring") // SpringのAOP機能のためにクラスをopenにする
    // id("org.springframework.boot") version "3.5.3"
    id("io.spring.dependency-management")
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // Webアプリケーション、JPAに必要な基本的な依存関係
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.xerial:sqlite-jdbc") // SQLite JDBC Driver 
    implementation("org.hibernate.orm:hibernate-community-dialects")

    // OkHttp
    implementation("com.squareup.okhttp3:okhttp:4.12.0")
    // Jackson
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

    // KotlinでEntityを使う場合に推奨される依存関係
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.springframework.boot:spring-boot-starter-validation") // バリデーション

    // Jsoup for HTML parsing
    implementation("org.jsoup:jsoup:1.17.2")

    // Test
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test")
    testImplementation("io.mockk:mockk:1.13.12") 
}

java {
    sourceCompatibility = JavaVersion.VERSION_17 // Java Versionを指定
}
