Feature: Login de usuario en la aplicación

  @login
  Scenario: El usuario es redireccionado cuando ingresa credenciales correctas
    Given 3. El usuario se encuentra en la pagina de login
    When 3. El usuario ingresa un usuario y contraseña validos
    And 3. Presiona el botón de ingresar
    Then 3. El usuario es redirigido a la pagina de servicios para el usuario

  Scenario: El usuario obtiene sus tokens cuando ingresa credenciales correctas
    Given 4. El usuario se encuentra en la pagina de login
    When 4. El usuario ingresa un usuario y contraseña validos
    And 4. Presiona el botón de ingresar
    Then 4. El usuario recibe sus tokens de identificacion y acceso
