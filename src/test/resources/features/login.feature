Feature: Login en ParaBank
  Como cliente de ParaBank
  Quiero iniciar sesion con mis credenciales
  Para poder acceder a mi cuenta

  Scenario: Login exitoso con credenciales validas
    Given que el cliente esta en la pagina de login de Parabank
    When ingresa el usuario "usuarioqa3" y la clave "contraseña1"
    Then deberia ver su pagina de cuentas

  Scenario: Login fallido con credenciales invalidas
    Given que el cliente esta en la pagina de login de Parabank
    When ingresa el usuario "usuarioinventado999" y la clave "claveinvalida"
    Then deberia ver un mensaje de error de login