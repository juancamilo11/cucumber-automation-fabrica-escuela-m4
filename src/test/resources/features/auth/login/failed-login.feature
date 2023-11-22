Feature: Login de usuario en la aplicación

  @login
  Scenario: Credenciales faltantes
    Given 1. El usuario esta en la pagina de login
    When 1. El usuario no ingresa credenciales alguno de los campos
    And 1. Hace click en el boton de ingresar
    Then 1. Los mensajes de error de los campos son mostrados

  Scenario: Credenciales incorrectas
    Given 2. El usuario esta en la pagina de login
    When 2. El usuario ingresa credenciales incorrectas
    And 2. Presiona el boton de ingresar
    Then 2. El mensaje de error es mostrado - Credenciales incorrectas

