# Módulo Mobile

Este módulo automatiza escenarios funcionales en una aplicación Android mediante Appium.

## Objetivo

Validar el flujo de compra desde login hasta confirmación usando datos cargados desde JSON y automatización móvil.

## Prerrequisitos

- Java 17+
- Maven 3.8+
- Appium Server activo en `http://127.0.0.1:4723/wd/hub`
- Emulador Android o dispositivo físico conectado
- Android SDK configurado

## Instalación

```bash
cd mobile
mvn clean install
```

## Ejecución

```bash
cd mobile
mvn test
```

## Estructura

```text
mobile/
├── pom.xml
├── run-on-device.bat
└── src/
    └── test/
        ├── java/
        └── resources/
            └── com/ligo/mobile/features/
```

## Casos principales

- Login con data JSON
- Selección de producto
- Agregado al carrito
- Checkout
- Confirmación final

## Reportes

Se genera reporte HTML de Cucumber en:

```text
mobile/target/cucumber-reports/mobile.html
```

## Decisiones técnicas

- Appium permite automatizar acciones reales sobre la app móvil.
- Se usa Java Client para control del dispositivo.
- Los datos se externalizan en JSON para mantener los escenarios más mantenibles.

## Alcance y limitaciones

- Requiere emulador o dispositivo Android configurado correctamente.
- Dependiente del estado del entorno móvil y tiempos de carga.
- No cubre pruebas de dispositivos iOS ni pruebas de performance.
