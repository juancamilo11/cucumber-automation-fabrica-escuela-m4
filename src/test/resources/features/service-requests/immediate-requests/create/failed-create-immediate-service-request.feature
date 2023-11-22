Feature: Creacion de Solicitudes de Servicio Inmediatas

  @solicitudesInmediatas
  Scenario:
    Given 7. El usuario ingresa a la vista de solicitudes de servicio
    When 7. El usuario llena el formulario de solicitud de servicio de forma incompleta
    And 7. El usuario hace click en el boton de crear solicitud de servicio
    Then 7. El usuario deberá ver los errores en los campos faltantes