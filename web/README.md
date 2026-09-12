# Módulo Web

Este módulo automatiza escenarios de navegación y compra en la aplicación web de SauceDemo.

## Objetivo

Validar el flujo principal de login, compra y confirmación en navegador usando Cucumber + Playwright.

## Prerrequisitos

- Java 17+
- Maven 3.8+
- Navegador compatible con Playwright
- Conexión a internet para acceder a SauceDemo

## Instalación

```bash
cd web
mvn clean install
```

## Ejecución

```bash
cd web
mvn test
```

## Estructura

```text
web/
├── pom.xml
└── src/
    └── test/
        ├── java/
        └── resources/
            └── com/ligo/web/features/
```

## Casos principales

- Login exitoso
- Login inválido
- Flujo de compra desde catálogo hasta confirmación

## Reportes

Se genera un reporte HTML de Cucumber en:

```text
web/target/cucumber-reports/web.html
```

## Decisiones técnicas

- Se usa Playwright para automatizar navegador de forma estable.
- Cucumber da legibilidad a los escenarios en lenguaje Gherkin.
- El runner usa `@CucumberOptions` para apuntar a recursos y step definitions.

## Alcance y limitaciones

- Validación funcional orientada a flujo de compra y login.
- Depende del estado de la página objetivo y de la disponibilidad del sitio.
- No cubre pruebas visuales avanzadas ni pruebas multi-navegador exhaustivas.
