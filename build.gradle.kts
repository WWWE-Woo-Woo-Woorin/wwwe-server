plugins {
	id("org.springframework.boot") version "3.0.2" apply false
	id("io.spring.dependency-management") version "1.1.0"
	kotlin("jvm") version "1.7.22"
	kotlin("plugin.spring") version "1.7.22"
	kotlin("plugin.jpa") version "1.7.22"
	kotlin("kapt") version "1.7.22"
}

java.sourceCompatibility = JavaVersion.VERSION_17

allprojects {
	group = "app.junsu"
	version = "0.0.1-SNAPSHOT"

	repositories {
		mavenCentral()
	}
}

subprojects {
	apply(plugin = "kotlin")
	apply(plugin = "kotlin-spring")
	apply(plugin = "io.spring.dependency-management")

	dependencies {

		// https://mvnrepository.com/artifact/org.jetbrains.kotlinx/kotlinx-coroutines-core
		implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")

		implementation("com.auth0:java-jwt:3.19.2")

		implementation("io.github.microutils:kotlin-logging:1.12.5")

		implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
		implementation("org.jetbrains.kotlin:kotlin-reflect")
		implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

		runtimeOnly("mysql:mysql-connector-java")

		testImplementation("org.springframework.boot:spring-boot-starter-test")
	}

	dependencyManagement {
		imports {
			mavenBom(org.springframework.boot.gradle.plugin.SpringBootPlugin.BOM_COORDINATES)
		}
	}

	tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
		kotlinOptions {
			freeCompilerArgs = listOf("-Xjsr305=strict")
			jvmTarget = "17"
		}
	}

	tasks.withType<Test> {
		useJUnitPlatform()
	}
}
