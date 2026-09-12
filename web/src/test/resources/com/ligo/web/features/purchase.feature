# language: es
@AUTOMATION_WEB
Característica: Flujo de compra en SauceDemo
  Como comprador
  Quiero completar el flujo de compra
  Para validar el escenario principal de la web

  @AUTOMATION_WEB_001
  Escenario: Completar compra con un usuario estándar válido
    Dado que el usuario está en la página de login de SauceDemo
    Cuando inicia sesión con las credenciales estándar
    Y agrega el producto "Sauce Labs Backpack" al carrito
    Y abre el carrito de compras
    Y completa el checkout con "Rosalba" "Pérez" y código postal "28001"
    Entonces el mensaje de confirmación debe ser "Thank you for your order!"

  @AUTOMATION_WEB_002
  Escenario: El login inválido es rechazado
    Dado que el usuario está en la página de login de SauceDemo
    Cuando inicia sesión con "locked_out_user" y "secret_sauce"
    Entonces el mensaje de error de login debe contener "locked out"
