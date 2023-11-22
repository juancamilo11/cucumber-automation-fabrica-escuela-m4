Feature: Creacion de Solicitudes de Servicio Inmediatas

  @solicitudesInmediatas
  Scenario:
    Given 8. El usuario ingresa a la vista de solicitudes de servicio
    When 8. El usuario llena el formulario de solicitud de servicio correctamente
    And 8. El usuario hace click en el boton de crear solicitud de servicio
    Then 8. El usuario deberá ver un popup de confirmacion de creacion de solicitud de servicio