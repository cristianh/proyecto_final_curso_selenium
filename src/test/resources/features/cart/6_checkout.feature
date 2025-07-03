#language:es
  @CheckoutShopping
  Característica: Proceso de pago

  //Redactar
  Como usuario del sitio web de comercio electrónico
  quiero hacer una compra
  Para obtener el producto que necesito

  Antecedentes: Iniciar sesión con credenciales correctas
    Dado que el usuario esta en la pagina de login
    Cuando el usario ingresa sus credenciales "pruebas@gmail.com" y "crusto2009"
    Y hace click en el botón de Log in


  @06_ConfirmPageShopping @EndToEnd
  Escenario: Proceso de pago exitoso
    Dado que el usuario ha agregado productos al carrito
    Y el usuario ha ingresado su información de envío
    Y el usuario ha seleccionado un método de pago válido
    Cuando el usuario confirma la compra
    Entonces el usuario debe recibir una confirmación de pedido "Thank you for your purchase!"
    Y el estado del pedido debe ser "Your order will be delivered in 5 days."