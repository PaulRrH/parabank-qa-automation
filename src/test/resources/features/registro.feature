Feature: Registro de nuevo cliente
  Como visitante
  Quiero registrarme como nuevo cliente
  Para poder acceder a mis servicios bancarios

  Scenario: Registro exitoso con datos validos
    Given que el visitante esta en la pagina de registro
    When completa el formulario de registro con datos validos
    Then deberia ver la confirmacion de que su cuenta fue creada