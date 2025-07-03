#language:es
@Login
Característica: Verificar Login
  Como usuario del sitio web de comercio electrónico
  Quiero poder ver y actualizar los datos de mi cuenta
  Para que mis datos de pedido y entrega sean correctos

  @02_LoginPageSuccess @Regretion
  Esquema del escenario: Iniciar sesión con credenciales "<descripcion>"
    Dado que el usuario esta en la pagina de login
    Cuando el usario ingresa sus credenciales "<email>" y "<password>"
    Y hace click en el botón de Log in
    Entonces el sistema deberia mostrarle el mensaje "<mensaje>"
    Ejemplos:
      | email             | password    | descripcion | mensaje                    |
      | pruebas@gmail.com | crusto2009  | correctas   | All Products               |
      | pruebas@gmail.com | crusto20091 | incorrectas | Invalid email or password. |