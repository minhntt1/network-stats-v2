# Network Stats v2 — Project Layout

## Directory layout

| Folder             | Purpose                                                                                                                      |
| ------------------ | ---------------------------------------------------------------------------------------------------------------------------- |
| `/`                | Repository root                                                                                                              |
| `/docs`            | Documents for the project                                                                                                    |
| `/netstats-common` | Common classes, packages and libraries used by both `worker` and `web` source code                                           |
| `/netstats-worker` | The implementation of network statistics workers, how they ingest data from target devices and analyze them — the core logic |
| `/netstats-web`    | Provide UI to query data and update devices' profile information — username, password, etc. — for workers                    |
| `/gradle`          | Configuration for Gradle, Gradle wrapper                                                                                     |

## Modules

### netstats-common

Shared library module, applied as `java-library`. Declares and exposes to `worker` and `web`:

- MyBatis core, MyBatis-Spring and MyBatis Spring Boot autoconfigure
- Spring Boot starter base + `spring-boot-starter-data-jdbc` (Hikari + JDBC)
- MySQL driver (`runtimeOnly`)
- Lombok (compile-only + annotation processor)
- Test stack (`spring-boot-starter-test`, JUnit Platform launcher)

### netstats-worker

Executable Spring Boot application (base package `com.home.netstats.v2.worker`).
Core ingestion / analysis workers that poll target devices and persist normalized data.

### netstats-web

Executable Spring Boot application (base package `com.home.netstats.v2.web`).
UI to query stored data and manage device profiles used by the workers.

## Testing

See [Testing](testing.md) for how to run the integration tests (Testcontainers,
MySQL, prerequisites).

## Build

Full build (skips tests):

```bash
./gradlew clean build -x test
```

Run a module:

```bash
./gradlew :netstats-worker:bootRun
./gradlew :netstats-web:bootRun
```

## Convention

- Base package for all modules: `com.home.netstats.v2`
- Java version: 25