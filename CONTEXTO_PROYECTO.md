# Contexto del proyecto — para retomar con Claude en otra máquina

> Pegá este documento completo al inicio de una conversación nueva con Claude para que retome el proyecto exactamente donde quedó.

## Qué es este proyecto

`parabank-qa-automation` es un proyecto de aprendizaje/portfolio: estoy construyendo un framework de automatización QA para la app demo **ParaBank** (banco de pruebas de Parasoft), simulando cómo trabaja un QA automatizador en una empresa de servicios IT como **NTTDATA**.

El proyecto está pensado para demostrar los requisitos de una vacante real de "QA Automatizador":
- Automatización: Selenium WebDriver, Rest Assured, Serenity (cubiertos por el stack elegido)
- Test Management: Gherkin (cubierto); Jira + Xray (pendiente, opcional — Xray importa `.feature` de Cucumber directamente)
- Testing continuo: se usará GitHub Actions como equivalente de Jenkins/Bitbucket (mismo concepto de pipeline, distinta herramienta)
- BDD (cubierto, es la base de todo el proyecto)
- Fuera de alcance, explícitamente: **Karate DSL** (alternativa a Rest Assured, no necesaria), **Appium** (mobile, ParaBank es web), **certificación ISTQB** (es teoría/examen aparte, no bloquea el proyecto)

## Cómo quiero que Claude trabaje conmigo (MUY IMPORTANTE)

- **Sos mi mentor**, no mi ejecutor. Sé QA básico pero no soy experto — explicame el *por qué* de cada decisión (Page Object Model, BDD, estructura de carpetas, Git, etc.), no solo me des el resultado.
- **Guiame paso a paso, no escribas el código vos.** Decime exactamente qué archivo crear, en qué carpeta, y qué código pegar — pero soy yo quien lo escribe/pega y ejecuta en mi propio IDE. No uses herramientas de escritura de archivos de código directamente en mi proyecto salvo que te lo pida explícitamente.
- Trabajo en **IntelliJ IDEA 2026.2**, y tengo **Postman** instalado para explorar APIs manualmente antes de automatizarlas con Rest Assured.
- Sabías (recomiendo repetirle esto a la IA en la nueva sesión) que cuando algo falla, preferí que me guíes a diagnosticar (ver logs, probar manualmente, expandir stack traces) en vez de darme la solución directa de una — así aprendo a debuggear.

## Stack técnico

- Java 21, Maven
- Serenity BDD 5.3.11 + Cucumber 7.34.6 + JUnit 4 (runner `CucumberWithSerenity`)
- serenity-rest-assured (para pruebas de API, aún no implementadas)
- Chrome vía Serenity WebDriver, screenshots en cada acción, navegador nuevo por escenario (config en `serenity.conf`)

**Nota de deprecación conocida (no bloqueante):** `net.serenitybdd.cucumber.CucumberWithSerenity` e `io.cucumber.junit.CucumberOptions` están deprecados desde Serenity 5.0.0 y se van a eliminar en Serenity 6.0.0, a favor de JUnit 5 (`@Suite` + `@IncludeEngines("cucumber")`). Por ahora sigue funcionando perfecto en la versión 5.3.11 que usamos — es un tema de modernización futura, no urgente.

## Estado actual del proyecto (al 2026-08-11)

Ya armamos el primer escenario BDD completo y funcionando de punta a punta: **login exitoso en ParaBank**.

Estructura creada:
```
src/test/java/com/parabank/qa/
  ├── api/                  (vacío, próximo paso: Rest Assured)
  ├── pages/
  │     └── LoginPage.java
  ├── runners/
  │     └── TestRunner.java
  └── stepdefinitions/
        └── LoginStepDefinitions.java
src/test/resources/features/
  └── login.feature
```

- **`LoginPage.java`**: Page Object con `@FindBy` para username, password y botón de login (selectores sacados inspeccionando el HTML real con DevTools).
- **`login.feature`**: escenario en español, Given/When/Then, con credenciales de un usuario registrado manualmente en el demo público.
- **`LoginStepDefinitions.java`**: conecta el Gherkin con `LoginPage`. Valida el login chequeando que la URL final contenga `overview.htm`.
- **`TestRunner.java`**: dispara todo con `@RunWith(CucumberWithSerenity.class)`.

**Dato importante sobre los datos de prueba:** el demo público de ParaBank resetea su base de datos periódicamente, así que los usuarios registrados no persisten para siempre. Si el test de login empieza a fallar (se queda en `login.htm` en vez de llegar a `overview.htm`), probablemente haya que **registrar un usuario nuevo** en `https://parabank.parasoft.com/parabank/register.htm` y actualizar las credenciales en el `.feature`.

## Git / GitHub

- Repo inicializado y publicado en: **https://github.com/PaulRrH/parabank-qa-automation** (rama `main`)
- Ya hay un primer commit con todo lo descrito arriba.
- Identidad de Git configurada: Paul / paulalexander2909@gmail.com

## Próximos pasos posibles (a elección)

1. **Segundo escenario**: login con credenciales inválidas (caso negativo), reutilizando el mismo `LoginPage`.
2. **CI con GitHub Actions**: que el test corra automáticamente en cada push, con badge de "tests passing" en el README.
3. **Pruebas de API con Rest Assured**: explorando primero un endpoint en Postman, después automatizando en `.../api`.
4. Más adelante: Jira + Xray (documentar escenarios), y una demostración conceptual de cómo se vería un `Jenkinsfile` equivalente al workflow de GitHub Actions.
