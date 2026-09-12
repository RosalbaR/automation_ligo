# Diseño de pruebas

## 1. Casos seleccionados

### Web

| ID | Escenario | Prioridad | Técnica | Automatiza |
| --- | --- | --- | --- | --- |
| WEB-01 | Completar compra con usuario estándar válido en SauceDemo | P0 | Cucumber + Selenium / Screenplay | Sí |
| WEB-02 | Login inválido con usuario bloqueado | P1 | Cucumber + Selenium / Screenplay | Sí |
| WEB-03 | Validación de flujo principal de compra: catálogo → carrito → checkout → confirmación | P0 | Cucumber + Selenium / Screenplay | Sí |

### API

| ID | Escenario | Prioridad | Técnica | Automatiza |
| --- | --- | --- | --- | --- |
| API-01 | Login exitoso con credenciales válidas | P0 | Karate DSL | Sí |
| API-02 | Login fallido con credenciales inválidas | P1 | Karate DSL | Sí |
| API-03 | Crear reserva con datos válidos | P0 | Karate DSL | Sí |
| API-04 | Consultar reserva existente | P0 | Karate DSL | Sí |
| API-05 | Consultar reserva inexistente | P1 | Karate DSL | Sí |

### Mobile

| ID | Escenario | Prioridad | Técnica | Automatiza |
| --- | --- | --- | --- | --- |
| MOB-01 | Compra desde login hasta confirmación usando data JSON | P0 | Appium + Cucumber/Step Definitions | Sí |
| MOB-02 | Inicio de sesión con datos cargados desde JSON | P1 | Appium + Cucumber/Step Definitions | Sí |
| MOB-03 | Flujo completo de compra: seleccionar producto, agregar al carrito, checkout y confirmación | P0 | Appium + Cucumber/Step Definitions | Sí |

## 2. Justificación de selección

Se priorizaron los casos con mayor impacto funcional, riesgo de negocio y facilidad de automatización:

- Web: el flujo principal de compra y la validación del login bloqueado representan la experiencia crítica del usuario.
- API: la autenticación y la gestión de reservas cubren tanto el éxito como la validación de errores, que son puntos clave de seguridad y robustez.
- Mobile: el flujo completo de compra desde login hasta confirmación refleja la operación principal de la app y es el escenario más representativo del negocio.

## 3. Cobertura por módulo

| Módulo | Cobertura principal | Observación |
| --- | --- | --- |
| Web | Login y compra | Se cubre el flujo crítico y la validación negativa |
| API | Autenticación y reservas | Se cubren escenarios positivos y de error |
| Mobile | Compra desde app | Se valida la experiencia del usuario en dispositivo |

## 4. Evidencia técnica de los escenarios

- Web: archivo `web/src/test/resources/com/ligo/web/features/purchase.feature`
- API: archivos `api/src/test/resources/com/ligo/api/features/Auth.feature` y `Booking.feature`
- Mobile: archivo `mobile/src/test/resources/com/ligo/mobile/features/mobile_purchase.feature`

## 5. Conclusión

El diseño de pruebas se enfoca en los flujos de mayor valor para el negocio y en los casos que permiten validar tanto el camino feliz como los errores más relevantes. Esto entrega una base sólida para automatización, trazabilidad y ejecución continua.
