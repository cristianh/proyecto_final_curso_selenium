#language:es
@Login
Característica: Verificar Login
  Como usuario del sitio web de comercio electrónico
  Quiero poder ver y actualizar los datos de mi cuenta
  Para que mis datos de pedido y entrega sean correctos

  @LoginPageSuccess02 @Regretion
  Esquema del escenario: : Iniciar sesión con credenciales "<descripcion>"
    Dado que el usuario esta en la pagina de login
    Cuando el usario ingresa sus credenciales "<email>" y "<password>"
    Y hace click en el botón de Log in
    Entonces el sistema deberia mostrarle el mensaje "<mensaje>"
    Ejemplos:
      | email             | password    | descripcion | mensaje                    |
      | pruebas@gmail.com | crusto2009  | correctas   | All Products               |
      | pruebas@gmail.com | crusto20091 | incorrectas | Invalid email or password. |



  @acc02
  Escenario: Actualización de información personal
    Dado que el usuario ha iniciado sesión en su cuenta
    Cuando el usuario accede a la página de configuración de la cuenta
    Y actualiza su información personal con nuevos datos
    Y guarda los cambios
    Entonces el sistema debe mostrar un mensaje de confirmación y los datos del usuario deben actualizarse correctamente

  @acc04
  Escenario: Agregar Dirección de Envío
    Dado que el usuario ha iniciado sesión en su cuenta
    Cuando el usuario accede a la sección de direcciones de envío
    Y agrega una nueva dirección de envío con detalles válidos
    Y guarda la nueva dirección
    Entonces el sistema debe mostrar un mensaje de confirmación y la dirección de envío debe estar asociada a la cuenta del usuario

