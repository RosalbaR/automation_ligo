# language: es
Característica: Flujo de compra en la aplicación móvil
  Como usuario de la aplicación móvil
  Quiero iniciar sesión, seleccionar un producto, agregarlo al carrito y completar la compra
  Para finalizar una compra desde la app

  Escenario: Compra desde login hasta confirmación usando data JSON
    Dado que el usuario carga la data del JSON
    Y que el usuario inicia sesión con la data del JSON
    Y que el usuario está en la pantalla de productos
    Cuando selecciona el producto de la data del JSON
    Y agrega el producto al carrito
    Y abre el carrito
    Y continúa con el checkout
    Y completa el checkout con la data del JSON
    Y confirma la compra
    Entonces se muestra la confirmación de compra
