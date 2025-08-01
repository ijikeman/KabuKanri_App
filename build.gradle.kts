plugins {
    kotlin("jvm") version "2.2.0" apply false
    kotlin("plugin.jpa") version "2.2.0" apply false
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
}