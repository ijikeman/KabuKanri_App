plugins {
    kotlin("jvm") // Kotlin version
    kotlin("plugin.spring") // Kotlin version
    kotlin("plugin.jpa") // デフォルトコンストラクタを設定することを回避する
    id("io.spring.dependency-management") // 親子モジュールで必要
    id("org.springframework.boot") // implementationでspringframework.bootを使っている為、指定
}

java {
    sourceCompatibility = JavaVersion.VERSION_17 // Java Versionを指定
}

dependencies {
    // Webアプリケーション、JPAに必要な基本的な依存関係
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")

    // データベース関連
    implementation("org.xerial:sqlite-jdbc") // SQLite JDBC Driver 
    implementation("org.hibernate.orm:hibernate-community-dialects")

    // Kotlin関連（重複を削除）
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.springframework.boot:spring-boot-starter-validation") // バリデーション
    
    // JSON処理
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

    // HTML解析
    implementation("org.jsoup:jsoup") // 親プロジェクトでバージョン管理する場合

    // Test
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test")
    testImplementation("io.mockk:mockk") // 親プロジェクトでバージョン管理
}
