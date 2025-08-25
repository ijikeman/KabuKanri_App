plugins {
    kotlin("jvm") version "2.0.10" apply false
    kotlin("plugin.jpa") version "2.0.10" apply false
    kotlin("plugin.spring") version "2.0.10" apply false
    id("org.springframework.boot") version "3.5.3" apply false
    id("io.spring.dependency-management") version "1.1.7" apply false
}

subprojects {
    // Repository設定
    repositories {
        mavenCentral()
    }
    

    // 親プロジェクト側でバージョン管理
    // プラグインを適用
    apply(plugin = "io.spring.dependency-management")   
    // 依存関係管理の設定
    configure<io.spring.gradle.dependencymanagement.dsl.DependencyManagementExtension> {
        dependencies {
            dependency("org.jsoup:jsoup:1.17.2")
            dependency("io.mockk:mockk:1.13.8")
        }
    }
}
