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
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa") // webでも使う
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
 
    // KotlinでEntityを使う場合に推奨される依存関係
    implementation("org.jetbrains.kotlin:kotlin-reflect") // webでは使わないため

    // `developmentOnly` を使うと、開発時(bootRun)のみ依存関係が追加されます
    // H2データベースドライバ
    developmentOnly("com.h2database:h2")
    // 開発時のホットリロードなどを有効にするDevTools
    implementation("org.springframework.boot:spring-boot-devtools")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test")
    implementation(project(":modules:stock")) // stockを有効にする
}

java {
    sourceCompatibility = JavaVersion.VERSION_21 // Java Versionを指定
}


// // Spring Bootアプリケーションの起動クラスを指定します
// tasks.getByName<org.springframework.boot.gradle.tasks.run.BootRun>("bootRun") {
//     mainClass.set("com.example.web.ApplicationKt") // あなたのSpring Bootアプリケーションのメインクラス名に合わせてください
// }
