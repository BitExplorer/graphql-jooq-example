import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jooq.meta.jaxb.Property

plugins {
  id("org.springframework.boot") version "2.5.0"
  id("io.spring.dependency-management") version "1.0.11.RELEASE"
  id("nu.studer.jooq") version "5.2.1"
  kotlin("jvm") version "1.5.10"
  kotlin("plugin.spring") version "1.5.10"
}

group = "me.jjeda"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
  mavenCentral()
}

dependencies {
  implementation("org.springframework.boot:spring-boot-starter-jooq")
  implementation("org.springframework.boot:spring-boot-starter-web")
  implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
  implementation("org.jetbrains.kotlin:kotlin-reflect")
  implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
  implementation("com.graphql-java-kickstart:graphql-spring-boot-starter:11.0.0")
  implementation("com.graphql-java-kickstart:graphql-java-tools:11.0.0")
  runtimeOnly("com.graphql-java-kickstart:altair-spring-boot-starter:11.0.0")
  runtimeOnly("com.graphql-java-kickstart:graphiql-spring-boot-starter:11.0.0")
  runtimeOnly("com.graphql-java-kickstart:voyager-spring-boot-starter:11.0.0")
  runtimeOnly("com.graphql-java-kickstart:playground-spring-boot-starter:11.0.0")
  runtimeOnly("mysql:mysql-connector-java")
  jooqGenerator("mysql:mysql-connector-java:6.0.3")
  testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<KotlinCompile> {
  kotlinOptions {
    freeCompilerArgs = listOf("-Xjsr305=strict")
    jvmTarget = "11"
  }
}

tasks.withType<Test> {
  useJUnitPlatform()
}

jooq {
  version.set("3.14.7")  // default (can be omitted)
  edition.set(nu.studer.gradle.jooq.JooqEdition.OSS)  // default (can be omitted)

  configurations {
    create("main") {  // name of the jOOQ configuration
      generateSchemaSourceOnCompilation.set(false)  // default (can be omitted)

      jooqConfiguration.apply {
        logging = org.jooq.meta.jaxb.Logging.WARN
        jdbc.apply {
          driver = "com.mysql.cj.jdbc.Driver"
          url = "jdbc:mysql://localhost:3306/jooq_example?serverTimezone=UTC&characterEncoding=UTF-8&useSSL=false"
          user = "root"
          password = "zigzag"
        }
        generator.apply {
          name = "org.jooq.codegen.DefaultGenerator"

          database.apply {
            name = "org.jooq.meta.mysql.MySQLDatabase"
            inputSchema = "jooq_example"
            includes = "goods"
            excludes = ""
          }

          generate.apply {
            isRelations = true
            isDeprecated = false
            isRecords = true
            isImmutablePojos = true
            isFluentSetters = true
          }
          strategy.name = "org.jooq.codegen.DefaultGeneratorStrategy"
        }
      }
    }
  }
}
