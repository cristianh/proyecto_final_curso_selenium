#language:es
@Checkout
Característica: Crear una orden

  Como usuario del sitio web de comercio electrónico
  quiero hacer una compra
  Para obtener el producto que necesito

  Antecedentes: Iniciar sesión con credenciales correctas
    Dado que el usuario esta en la pagina de login
    Cuando el usario ingresa sus credenciales "pruebas@gmail.com" y "crusto2009"
    Y hace click en el botón de Log in

  @05_Checkout @EndToEnd
  Escenario: Agregar productos al carrito
    Cuando el usuario busca los productos y los agrega al carrito
      | nombre  |
      | banana  |
      | apple   |
      | t-shirt |
    Entonces el carrito debe mostrar "3" y el total debe ser actualizado correctamente