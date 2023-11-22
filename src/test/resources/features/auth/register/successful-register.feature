Feature: Registro de usuario en la aplicación

  @register
  Scenario: El usuario ingresa la informacion correcta y completamente
    Given 6. El usuario se encuentra en la pagina de registro
    When 6. El usuario ingresa los datos correctamente
    And 6. Presiona el botón de registrarse
    Then 6. El usuario es registrado correctamente y es rediriido a la pagina de login