#language:es
@Checkout
Característica: Historial de pedidos

  Como usuario del sitio web de comercio electrónico
  quiero poder ver todos mis pedidos
  Para consultar los pedido realizados anteriormente

  Antecedentes: Iniciar sesión con credenciales correctas
    Dado que el usuario esta en la pagina de login
    Cuando el usario ingresa sus credenciales "pruebas@gmail.com" y "crusto2009"
    Y hace click en el botón de Log in


  @08_CheckoutHistory @EndToEnd
  Escenario: validar historial de pedidos realizados
    Dado que el usuario ha realizado pedidos anteriormente
    Cuando el usuario accede al historial de pedidos
    Entonces el usuario debe ver la lista de sus pedidos anteriores con detalles
