# Diccionario de patrones Gherkin — Banca vs. Seguros

> Referencia de estudio: la **estructura** (`Feature` → `Scenario` → `Given/When/Then`) es siempre la misma en cualquier metodología BDD, sin importar la empresa o industria. Lo único que cambia es el **vocabulario del negocio**. Estos ejemplos comparan cómo se vería el mismo patrón de comportamiento en una app bancaria vs. una app de seguros.

---

## 1. Autenticación (Login)

**Banca**
```gherkin
Feature: Login en la banca en linea
  Como cliente del banco
  Quiero iniciar sesion con mis credenciales
  Para poder acceder a mi cuenta

  Scenario: Login exitoso con credenciales validas
    Given que el cliente esta en la pagina de login
    When ingresa el usuario "usuarioqa2" y la clave "contraseña1"
    Then deberia ver su pagina de cuentas

  Scenario: Login fallido con credenciales invalidas
    Given que el cliente esta en la pagina de login
    When ingresa el usuario "usuarioinventado999" y la clave "claveinvalida"
    Then deberia ver un mensaje de error de login
```

**Seguros**
```gherkin
Feature: Login en el portal del asegurado
  Como asegurado
  Quiero iniciar sesion con mis credenciales
  Para poder acceder a mis polizas

  Scenario: Login exitoso con credenciales validas
    Given que el asegurado esta en la pagina de login del portal
    When ingresa el usuario "asegurado01" y la clave "clave1234"
    Then deberia ver el resumen de sus polizas

  Scenario: Login fallido con credenciales invalidas
    Given que el asegurado esta en la pagina de login del portal
    When ingresa el usuario "noexiste" y la clave "incorrecta"
    Then deberia ver un mensaje indicando que las credenciales son invalidas
```

---

## 2. Registro / formulario con validaciones

**Banca**
```gherkin
Feature: Registro de nuevo cliente

  Scenario: Registro exitoso con datos validos
    Given que el visitante esta en la pagina de registro
    When completa el formulario con datos validos
    Then deberia ver un mensaje de bienvenida y su cuenta creada

  Scenario: Registro fallido por usuario ya existente
    Given que el visitante esta en la pagina de registro
    When completa el formulario con un nombre de usuario ya registrado
    Then deberia ver un mensaje indicando que el usuario ya existe
```

**Seguros**
```gherkin
Feature: Cotizacion de poliza de auto

  Scenario: Cotizacion exitosa con datos validos
    Given que el visitante esta en la pagina de cotizacion
    When completa el formulario con los datos del vehiculo y el conductor
    Then deberia ver el monto estimado de la prima

  Scenario: Cotizacion fallida por datos incompletos
    Given que el visitante esta en la pagina de cotizacion
    When completa el formulario sin el numero de licencia de conducir
    Then deberia ver un mensaje indicando que el campo es obligatorio
```

---

## 3. Flujo transaccional / de negocio principal

**Banca**
```gherkin
Feature: Transferencia de fondos

  Scenario: Transferencia exitosa entre cuentas propias
    Given que el cliente esta logueado
    When transfiere "100" desde la cuenta "12345" hacia la cuenta "67890"
    Then deberia ver el mensaje de transferencia exitosa

  Scenario: Transferencia fallida por fondos insuficientes
    Given que el cliente esta logueado
    When transfiere "999999" desde la cuenta "12345" hacia la cuenta "67890"
    Then deberia ver un mensaje de fondos insuficientes
```

**Seguros**
```gherkin
Feature: Presentacion de reclamo (siniestro)

  Scenario: Reclamo presentado exitosamente
    Given que el asegurado esta logueado
    When presenta un reclamo para la poliza "POL-001" con monto "500"
    Then deberia ver el mensaje de reclamo recibido

  Scenario: Reclamo rechazado por poliza vencida
    Given que el asegurado esta logueado
    When presenta un reclamo para la poliza "POL-999" que esta vencida
    Then deberia ver un mensaje indicando que la poliza no esta vigente
```

---

## 4. Recuperacion de contraseña

**Banca**
```gherkin
Feature: Recuperacion de contraseña

  Scenario: Solicitud exitosa de recuperacion
    Given que el cliente esta en la pagina "Olvide mi contraseña"
    When ingresa su usuario y correo registrado
    Then deberia ver un mensaje confirmando el envio de instrucciones
```

**Seguros**
```gherkin
Feature: Recuperacion de contraseña del asegurado

  Scenario: Solicitud exitosa de recuperacion
    Given que el asegurado esta en la pagina "Olvide mi contraseña"
    When ingresa su numero de poliza y correo registrado
    Then deberia ver un mensaje confirmando el envio de instrucciones
```

---

## 5. Busqueda / filtrado de un listado

**Banca**
```gherkin
Feature: Historial de transacciones

  Scenario: Filtrar transacciones por rango de fechas
    Given que el cliente esta en la pagina de historial de cuenta
    When filtra las transacciones entre "01/01/2026" y "31/01/2026"
    Then deberia ver unicamente las transacciones de ese rango
```

**Seguros**
```gherkin
Feature: Historial de polizas

  Scenario: Filtrar polizas por estado
    Given que el asegurado esta en la pagina de sus polizas
    When filtra las polizas por estado "Activas"
    Then deberia ver unicamente las polizas activas
```

---

## 6. Edicion de perfil / datos personales

**Banca**
```gherkin
Feature: Edicion de datos personales

  Scenario: Actualizacion exitosa de telefono de contacto
    Given que el cliente esta logueado y en su perfil
    When actualiza su numero de telefono
    Then deberia ver un mensaje confirmando el cambio
```

**Seguros**
```gherkin
Feature: Edicion de datos del asegurado

  Scenario: Actualizacion exitosa de direccion
    Given que el asegurado esta logueado y en su perfil
    When actualiza su direccion de residencia
    Then deberia ver un mensaje confirmando el cambio
```

---

## 7. Carga de documentos

**Banca**
```gherkin
Feature: Carga de documentos para apertura de cuenta

  Scenario: Carga exitosa de documento de identidad
    Given que el cliente esta en el paso de verificacion de identidad
    When sube una foto valida de su documento de identidad
    Then deberia ver el estado "Documento recibido"
```

**Seguros**
```gherkin
Feature: Carga de evidencia para un reclamo

  Scenario: Carga exitosa de fotos del siniestro
    Given que el asegurado esta completando un reclamo
    When sube fotos como evidencia del siniestro
    Then deberia ver el estado "Evidencia recibida"
```

---

## 8. Cierre de sesion (logout)

**Banca**
```gherkin
Feature: Cierre de sesion

  Scenario: Logout exitoso
    Given que el cliente esta logueado
    When hace click en "Cerrar sesion"
    Then deberia volver a la pagina de login
```

**Seguros**
```gherkin
Feature: Cierre de sesion del asegurado

  Scenario: Logout exitoso
    Given que el asegurado esta logueado
    When hace click en "Cerrar sesion"
    Then deberia volver a la pagina de login del portal
```

---

## Resumen: lo que NO cambia entre industrias

- La estructura: `Feature` (contexto/objetivo) → `Scenario` (caso concreto) → `Given/When/Then` (precondicion/accion/resultado esperado).
- La practica de incluir siempre un caso **positivo** (happy path) y al menos un caso **negativo** (error esperado) por funcionalidad.
- Los nombres de los `Scenario` describen el **resultado del negocio**, no detalles tecnicos (nunca dice "click en boton con id=submit", dice "transferencia exitosa").

## Lo que SI cambia

- El **actor** (cliente, asegurado, paciente, comprador, segun la industria).
- El **vocabulario del dominio** (cuenta/poliza, transferencia/reclamo, saldo/cobertura).
- Las **reglas de negocio especificas** detras de cada escenario (limites de transferencia, vigencia de polizas, etc.), aunque el patron de prueba sea el mismo.
