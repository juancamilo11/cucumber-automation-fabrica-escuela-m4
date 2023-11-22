Feature: Registro de usuario en la aplicación

  @register
  Scenario: Registro invalido por informacion del usuario faltante
    Given 5. El usuario esta en la pagina de registro
    When 5. El usuario no ingresa completamente alguno de los campos
    And 5. Presiona el boton de registrarse
    Then 5. Los mensajes de error de los campos son mostrados

