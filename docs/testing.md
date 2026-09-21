# Testing

This document describes how to run tests for the `netstats-common` module (and
eventually for `worker` / `web`).

## Prerequisites

- **Docker** — Testcontainers integration tests spin up real MySQL 8.4
  containers.  Docker must be installed and the daemon running.
  ```bash
  docker info     # should show Server Version >= 26.x
  ```
- **JDK 25** — the build uses Gradle toolchain; verify with `java -version`.
- **Network** — the first run pulls the `mysql:8.4` image (~450 MB).

## Running the tests

### From the command line

```bash
# Run only common-module tests (fastest)
./gradlew :netstats-common:test

# Run the full build including all modules
./gradlew build
```

Test output goes to `netstats-common/build/reports/tests/test/index.html`.

All tests are **transactional** — mutations are rolled back so they never
leak between tests or pollute the shared seed data.

## Customising the database config

All Testcontainer constants are in
`netstats-common/src/test/resources/db.properties`:
- Container image, database name, credentials
- Hikari pool size, timeouts, isolation level
- MyBatis mapper location pattern

Change them without touching Java code.

## Troubleshooting

| Symptom                                                           | Most likely cause                |
|------------------------------------------------------------------|----------------------------------|
| `Cannot apply script …/schema.sql` or `…/data.sql`               | Container already seeded? Test is reprovisioned on fresh JVM; run `./gradlew clean` then retry. |
| `NoClassDefFoundError: org/testcontainers/shaded/…`               | Testcontainers dependency conflict; check `./gradlew dependencies` for version mismatches. |
| `Docker daemon is not running`                                    | Start Docker before running tests. |
| `Port … already allocated`                                        | Previous container not cleaned up by Ryuk; run `docker rm -f $(docker ps -aq --filter name=testcontainers)`. |