plugins {
    kotlin("jvm") version "2.0.10" apply false
    kotlin("plugin.jpa") version "2.0.10" apply false
    kotlin("plugin.spring") version "2.0.10" apply false // SpringのAOP機能のためにクラスをopenにする
    id("org.springframework.boot") version "3.5.3" apply false
    id("io.spring.dependency-management") version "1.1.7" apply false
}

// BOMを有効にしSubprojectのバージョン記載を回避する
import io.spring.gradle.dependencymanagement.dsl.DependencyManagementExtension
subprojects {
    apply(plugin = "io.spring.dependency-management")
    
    configure<DependencyManagementExtension> {
        imports {
            mavenBom("org.springframework.boot:spring-boot-dependencies:3.5.3")
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
        testLogging {
            events("started", "passed", "skipped", "failed")
            showStandardStreams = true
            exceptionFormat = org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
        }
    }
}
