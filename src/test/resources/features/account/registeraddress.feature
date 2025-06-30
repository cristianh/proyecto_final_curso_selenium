#language:es
@Register
Característica: Nuevo registro direccion

  Como visitante del sitio web de comercio electrónico
  Quiero poder registrar mi direccion de envio
  Para recibir mi pedido en mi domicilio

  Antecedentes: Iniciar sesión con credenciales correctas
    Dado que el usuario esta en la pagina de login
    Cuando el usario ingresa sus credenciales "pruebas@gmail.com" y "crusto2009"
    Y hace click en el botón de Log in

  @RegisterAddress03Alternative
  Escenario: Registro exitoso direccion de envio
    Dado que el usuario está en la página de registro de direccion
    Cuando el usuario completa el formulario de registro
    Y hace clic en el botón de enviar
    Entonces el usuario debe ser redirigido a la página del login

  @RegisterAddressList03 @EndToEnd
  Escenario: Registro exitoso direccion de envio
    Dado que el usuario está en la página de registro de direccion
    Cuando el usuario completa el formulario de registro con las direccion
      | country  | province        | mobilenumber | zipcode | address       | city    | state   |
      | Colombia | Quindio         | 300101011    | 0006030 | B las palamas | Armenia | Armenia |
      | Colombia | Valle del cauca | 333311111    | 0008030 | B las pola    | Cali    | Cali    |
    Entonces las diferentes direcciones se visualicen en la pagina