# Módulo API

Este módulo valida servicios REST con Karate DSL.

## Objetivo

Ejecutar pruebas automáticas sobre endpoints de autenticación y reservas para validar comportamiento esperado y manejo de errores.

## Prerrequisitos

- Java 17+
- Maven 3.8+
- Conexión a internet para consumir la API pública

## Instalación

```bash
cd api
mvn clean install
```

## Ejecución

```bash
cd api
mvn test
```

## Estructura

```text
api/
├── pom.xml
└── src/
    ├── main/
    └── test/
        ├── java/
        └── resources/
            └── com/ligo/api/features/
```

## Casos principales

- `Auth.feature`: login exitoso y fallido
- `Booking.feature`: crear reserva, consultar existente y consultar inexistente

## Reportes

Los resultados se ejecutan con JUnit + Karate y pueden revisarse en la salida de Maven y en reportes generados por la herramienta.

## Decisiones técnicas

- Karate reduce la complejidad de pruebas REST.
- Se usa `Runner.path("classpath:com/ligo/api/features")` para ejecutar los escenarios.
- Los scripts están organizados por dominio: autenticación y reservas.

## Alcance y limitaciones

- Requiere acceso a la API de prueba.
- Las validaciones dependen de la disponibilidad del servicio remoto.
- No cubre pruebas de carga ni seguridad avanzada.
