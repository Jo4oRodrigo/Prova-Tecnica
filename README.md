# Prova Técnica — Analista de Automação de Testes (Java / Selenium / RestAssured / PostgreSQL)

## Conteúdo do repositório
- `README.md` (este arquivo)
- `cenarios-de-teste.md` (Parte 0 e SQL - Parte E - cenários e planejamento)
- `pom.xml` (Maven)
- `src/test/java/com/company/ui/pages/LoginPage.java`
- `src/test/java/com/company/ui/utils/WebDriverFactory.java`
- `src/test/java/com/company/ui/tests/BaseUiTest.java`
- `src/test/java/com/company/ui/tests/LoginUiTests.java`
- `src/test/java/com/company/api/tests/LoginApiTests.java`
- `src/test/resources/config.properties`
- `sql/seed.sql`
- `sql/cleanup.sql`
- `.gitignore`

## Requisitos
- JDK 11+
- Maven 3.6+
- ChromeDriver (ou adapte WebDriverFactory) disponível no PATH
- Postgres (se rodar testes de integração com DB) ou Testcontainers

## Como rodar
### Testes de API
```bash
mvn -Dtest=com.company.api.tests.LoginApiTests test
```

### Testes UI (local)
```bash
mvn -Dtest=com.company.ui.tests.LoginUiTests test
```

### Configurações
Edite `src/test/resources/config.properties` para ajustar `base.url`, `browser` e timeouts.

## Observações
- Os testes presumem que usuários de teste foram criados via `sql/seed.sql`.
- Em CI, utilize Chrome em modo headless e configure chromedriver/action para executar browsers.
