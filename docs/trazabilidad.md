# Matriz de trazabilidad

Relación: Requerimiento o flujo → Caso de prueba → Test automatizado.

## 1. Web

| Requerimiento / flujo | Caso de prueba | Test automatizado | Archivo / automatización |
| --- | --- | --- | --- |
| Login válido en SauceDemo | WEB-01: Completar compra con un usuario estándar válido | Sí | `web/src/test/resources/com/ligo/web/features/purchase.feature` |
| Login inválido bloqueado | WEB-02: El login inválido es rechazado | Sí | `web/src/test/resources/com/ligo/web/features/purchase.feature` |
| Compra desde catálogo hasta confirmación | WEB-03: Flujo principal de compra | Sí | `PurchaseSteps.java` + `purchase.feature` |

### Evidencia técnica Web
- Escenario principal: `Completar compra con un usuario estándar válido`
- Escenario alternativo: `El login inválido es rechazado`
- Validación final: mensaje `Thank you for your order!`

## 2. API

| Requerimiento / flujo | Caso de prueba | Test automatizado | Archivo / automatización |
| --- | --- | --- | --- |
| Autenticación exitosa | API-01: Login exitoso con credenciales válidas | Sí | `api/src/test/resources/com/ligo/api/features/Auth.feature` |
| Autenticación fallida | API-02: Login fallido con credenciales inválidas | Sí | `api/src/test/resources/com/ligo/api/features/Auth.feature` |
| Crear reserva con datos válidos | API-03: Crear reserva con datos válidos | Sí | `api/src/test/resources/com/ligo/api/features/Booking.feature` |
| Consultar reserva existente | API-04: Consultar reserva existente | Sí | `api/src/test/resources/com/ligo/api/features/Booking.feature` |
| Consultar reserva inexistente | API-05: Consultar reserva inexistente | Sí | `api/src/test/resources/com/ligo/api/features/Booking.feature` |

### Evidencia técnica API
- Login: `POST /auth`
- Reservas: `POST /booking`, `GET /booking/{id}`
- Validación de errores: `404` para reserva no encontrada

## 3. Mobile

| Requerimiento / flujo | Caso de prueba | Test automatizado | Archivo / automatización |
| --- | --- | --- | --- |
| Compra desde login hasta confirmación en app móvil | MOB-01: Compra desde login hasta confirmación usando data JSON | Sí | `mobile/src/test/resources/com/ligo/mobile/features/mobile_purchase.feature` |
| Inicio de sesión con datos cargados desde JSON | MOB-02: Login con archivo JSON | Sí | `mobile/src/test/java/com/ligo/mobile/data/JsonDataLoader.java` + step definitions |
| Flujo de compra: selección, carrito, checkout y confirmación | MOB-03: Compra completa en la aplicación | Sí | `MobilePurchaseStepDefinitions.java` |

### Evidencia técnica Mobile
- La automatización usa `JsonDataLoader` para cargar datos desde JSON
- El flujo cubre: login, selección de producto, agregar al carrito, checkout y confirmación

## 4. Resumen general

| Módulo | Requisitos cubiertos | Casos automatizados |
| --- | --- | --- |
| Web | Login, flujo de compra, validación de errores | 2 escenarios principales |
| API | Autenticación, creación y consulta de reservas | 5 escenarios |
| Mobile | Login, compra, checkout, confirmación | 1 flujo principal |

La trazabilidad queda completa cuando cada requisito del negocio tiene uno o varios casos de prueba y cada caso tiene un test automatizado asociado.
