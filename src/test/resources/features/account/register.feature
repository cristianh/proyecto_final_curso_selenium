#language:es
@Register
Característica: Nuevo registro
  Como visitante del sitio web de comercio electrónico
  Quiero crear una cuenta
  Para comprar productos

  @RegisterSuccess01 @Regretion
  Escenario: Registro exitoso con informacion valida
    Dado que el usuario está en la página de registro
    Cuando el usuario completa la información válida
    Y el usuario hace clic en el boton de registrar
    Entonces el sistema debe mostrarle mensaje de registro exitoso

  @reg02
  Escenario: Registro con correo electrónico existente
    Dado que el usuario está en la página de registro
    Y el correo electrónico ya está registrado en el sistema
    Cuando el usuario intenta registrarse con ese correo electrónico
    Entonces el sistema debe mostrar un mensaje de error indicando que el correo electrónico ya está en uso

  @reg03
  Escenario: Registro sin completar todos los campos obligatorios
    Dado que el usuario está en la página de registro
    Cuando el usuario intenta registrarse sin completar todos los campos obligatorios
    Entonces el sistema debe mostrar un mensaje indicando que todos los campos obligatorios deben ser llenados

  @reg04
  Escenario: Contraseña débil durante el registro
    Dado que el usuario está en la página de registro
    Y el usuario elige una contraseña débil
    Cuando el usuario intenta registrarse con esa contraseña
    Entonces el sistema debe mostrar un mensaje indicando que la contraseña es débil y debe contener al menos X caracteres, letras y números

  @reg05
  Escenario: Confirmación de contraseña incorrecta durante el registro
    Dado que el usuario está en la página de registro
    Y el usuario ingresa una contraseña y una confirmación de contraseña que no coinciden
    Cuando el usuario intenta registrarse con esas contraseñas
    Entonces el sistema debe mostrar un mensaje indicando que las contraseñas no coinciden

  @reg06
  Escenario: Registro exitoso con detalles opcionales
    Dado que el usuario está en la página de registro
    Cuando el usuario completa el formulario de registro con información válida, incluyendo detalles opcionales
    Y hace clic en el botón de registro
    Entonces el usuario debe ser redirigido a la página de inicio de sesión
    Y recibir un mensaje de confirmación de registro
