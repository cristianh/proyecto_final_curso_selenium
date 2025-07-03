#language:es
@CreditCard
Característica: Nuevo registro direccion

  Como visitante del sitio web de comercio electrónico
  Quiero poder registrar mi direccion de envio
  Para recibir mi pedido en mi domicilio

  Antecedentes: Iniciar sesión con credenciales correctas
    Dado que el usuario esta en la pagina de login
    Cuando el usario ingresa sus credenciales "pruebas@gmail.com" y "crusto2009"
    Y hace click en el botón de Log in

 @CreditCardSuccessAlternative
  Escenario: Agregar Tarjeta de Crédito
    Cuando el usuario accede a la sección de información de pago
    Y agrega una nueva tarjeta de crédito con detalles válidos
    Y guarda la nueva tarjeta
    Entonces el sistema debe mostrar un mensaje de confirmación y la tarjeta de crédito debe estar asociada a la cuenta del usuario


  @04_CreditCardSuccessList @EndToEnd
  Escenario: Agregar Tarjetas de Crédito
    Cuando el usuario accede a la sección de información de pago
    Y agrega nuevas tarjetas de crédito con detalles válidos
      | cardname   | cardnumber       | datemoth | dateyear |
      | Mastercard | 5425233430109903 | 4        | 2080     |
      | Mastercard | 5425233430109903 | 12       | 2090     |
    Y guarda la nueva tarjeta
    Entonces el sistema debe mostrar un mensaje de confirmación y la tarjeta de crédito debe estar asociada a la cuenta del usuario