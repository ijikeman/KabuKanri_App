plugins {
    kotlin("jvm")
    kotlin("plugin.jpa")
    kotlin("plugin.spring")
    id("org.springframework.boot")
    id("io.spring.dependency-management")
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // Webアプリケーション、JPAに必要な基本的な依存関係
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa") // webでも使う
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
    implementation("org.springframework.boot:spring-boot-starter-cache")

    // KotlinでEntityを使う場合に推奨される依存関係
    // implementation("org.jetbrains.kotlin:kotlin-reflect") // webでは使わないため

    // `developmentOnly` を使うと、開発時(bootRun)のみ依存関係が追加されます
    // 開発時のホットリロードなどを有効にするDevTools
    // implementation("org.springframework.boot:spring-boot-devtools")

    // 本番環境用: SQLite JDBCドライバ
    implementation("org.xerial:sqlite-jdbc:3.50.2.0")
    implementation("org.springframework.boot:spring-boot-starter-jdbc") // Spring JDBC Template/JdbcClientに必要
    implementation("org.hibernate.orm:hibernate-community-dialects")
    // 開発環境用: H2データベースドライバ
    runtimeOnly("com.h2database:h2")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test")
    implementation(project(":modules:stock")) // stockを有効にする
}

java {
    sourceCompatibility = JavaVersion.VERSION_21 // Java Versionを指定
}

// Spring Bootアプリケーションの起動クラスを指定し、stockモジュールのクラスをクラスパスに含める
tasks.withType<org.springframework.boot.gradle.tasks.run.BootRun> {
    mainClass.set("com.example.ApplicationKt")
    sourceResources(project(":modules:stock").sourceSets.main.get())
}
