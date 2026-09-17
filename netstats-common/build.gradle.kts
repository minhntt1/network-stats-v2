plugins {
    `java-library`
    id("io.spring.dependency-management")
}

java {
    sourceCompatibility = JavaVersion.VERSION_25
    targetCompatibility = JavaVersion.VERSION_25
}

// Import the Spring Boot BOM so starter artifacts in this module
// (and transitively in worker/web) get managed versions.
dependencyManagement {
    imports {
        mavenBom("org.springframework.boot:spring-boot-dependencies:4.1.1")
    }
}

dependencies {
    // ---- MyBatis (shared by worker and web) ----
    api("org.mybatis:mybatis:3.5.19")
    api("org.mybatis:mybatis-spring:4.1.0")
    api("org.mybatis.spring.boot:mybatis-spring-boot-autoconfigure:4.1.0")

    // ---- Spring Boot base (shared by worker and web) ----
    api("org.springframework.boot:spring-boot-starter")

    // Hikari + basic JDBC
    api("org.springframework.boot:spring-boot-starter-data-jdbc")

    // MySQL driver
    runtimeOnly("com.mysql:mysql-connector-j")

    // ---- Lombok ----
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")

    // ---- Tests ----
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testCompileOnly("org.projectlombok:lombok")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    testAnnotationProcessor("org.projectlombok:lombok")
}