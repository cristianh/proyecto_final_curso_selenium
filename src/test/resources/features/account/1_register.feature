#language:es
@Register
Característica: Nuevo registro
  Como visitante del sitio web de comercio electrónico
  Quiero crear una cuenta
  Para comprar productos

  @01_RegisterSuccess
  Escenario: Registro exitoso con informacion valida
    Dado que el usuario está en la página de registro
    Cuando el usuario completa la información válida
    Y confirme la informacion con el boton de registrar
    Entonces el sistema debe mostrarle mensaje de registro exitoso



