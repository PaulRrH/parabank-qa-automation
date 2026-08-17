Feature: Login en ParaBank
  Como cliente de ParaBank
  Quiero iniciar sesion con mis credenciales
  Para poder acceder a mi cuenta

  Scenario: Login exitoso con credenciales validas
    Given que el cliente esta en la pagina de login de Parabank
    When ingresa el usuario "usuarioqa2" y la clave "contraseña1"
    Then deberia ver su pagina de cuentas

