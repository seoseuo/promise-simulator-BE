# Repository Guidelines

## Project Structure & Module Organization

This repository currently contains product-discovery documentation only. Keep
requirements and product decisions in `docs/`:

- `docs/product.md` defines the core user flow and example simulator output.
- `docs/business.md` captures profile fields, appointment inputs, and the
  expected simulation-result template.

The Spring Boot API lives in `src/main/java/com/promisesimulator`, configuration
in `src/main/resources`, and Java tests in `src/test/java`. Keep static assets
out of this backend unless the API needs to serve them directly.

## Development Commands

The backend uses Maven and Spring Boot. Run commands from the repository root:

```sh
mvn spring-boot:run -Dspring-boot.run.profiles=local  # start against local PostgreSQL
mvn test                                             # run automated tests
mvn package                                          # compile, test, and package the JAR
```

Provide `DB_URL`, `DB_USERNAME`, and `DB_PASSWORD` when local defaults do not
match your PostgreSQL instance.

## Documentation & Domain Conventions

Write product-facing documentation in Korean, matching the existing files.
Use concise Markdown headings and lists. Preserve the simulator output shape:
timeline entries should cover time, activity, expected situation, behavior, and
variables; summaries should include enjoyment, compatibility, satisfaction,
fatigue, and cost metrics. Mark optional inputs explicitly as `(선택)` and keep
choice labels consistent across documents.

For future code, use descriptive domain names such as `userProfile`,
`friendProfile`, `appointment`, and `simulationResult`. Prefer one concept per
module and avoid abbreviations that obscure business meaning.

## Testing Guidelines

Use Spring Boot Test and JUnit with each feature. Cover profile validation,
appointment input handling, and deterministic simulation-result formatting.
Name test classes after the subject, such as `AppointmentServiceTest`, and test
methods by behavior, such as `createsAppointmentWhenEndTimeIsUnspecified`.

## Commit & Pull Request Guidelines

No Git history is available, so no established commit convention can be
inferred. Use short imperative commits, for example `Add appointment form
validation`. Keep each commit focused. Pull requests should explain the user
impact, list validation performed, link relevant issues or decisions, and
include screenshots for UI changes. Update `docs/` whenever a product flow or
field changes.
