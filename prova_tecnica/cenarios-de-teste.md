## Parte 0 — Respostas
1. Tipos de teste:
    - Unitário: testa unidades isoladas.
    - Integração: testa interação entre módulos/serviços.
    - E2E: valida fluxo completo do usuário.
    - Prioridade CI/CD: priorizar unitários; executar integração e E2E selecionados.

2. Automação e ROI:
    - Vantagens: rapidez, regressão, economia a longo prazo.
    - Riscos: manutenção alta, falsa sensação de cobertura, dados mal gerenciados.

3. Teste de integração em microserviços:
    - Desafios: dependências distribuídas, consistência eventual, provisionamento.
    - Boas práticas: contract testing (Pact); usar mocks/stubs ou service virtualization.

4. Idempotência e consistência:
    - Usar transações/rollback, schemas temporários, seeds re-aplicáveis, testar idempotência.

5. Mock / Stub / Spy:
    - Stub: respostas pré-definidas (Mockito stubs)
    - Mock: verifica interações (Mockito)
    - Spy: envolve objeto real e monitora (Mockito.spy)

6. Boas práticas em Java:
    - Estrutura por pacotes, nomeação clara, isolamento, reuso via Page Objects/adapters.

7. Gestão de dados:
    - Versionar fixtures, usar migrations (Flyway), seeds, schemas efêmeros, snapshots.


## Parte A — Respostas
## Cenários de Teste — Sistema de Login (ADMIN, USER, VISITOR)

## Cenários
1. Login válido — USER (positivo)
2. Login válido — ADMIN (positivo)
3. Login inválido — senha incorreta (negativo)
4. Login com usuário inexistente (negativo)
5. Acesso negado — VISITOR tenta acessar rota protegida (segurança)
6. Bloqueio após 3 tentativas inválidas (segurança)
7. Sessão expirada / token inválido (segurança)
8. Tempo de carregamento aceitável (<= 5s) (performance)
9. Input malicioso (XSS) em campos de login (segurança)
10. Redirecionamento pós-login para página requisitada

## Classificação dos tipos de teste
- Unitário: validações pequenas de serviços (não cobertas aqui)
- Integração: contratos, bloqueio, persistência de tentativas
- E2E/UI: fluxo completo de login e redirecionamentos
- Segurança: XSS, autenticação, autorização
- Performance: tempo de carregamento do dashboard

## Automatização proposta
- API (RestAssured): casos 3,4,5,6,7
- UI (Selenium): casos 1,2,5,10, e validação visual de performance/esperas
- Manual/Exploratório: caso 9 (pentest), e testes de carga (uso de JMeter)

## Estrutura de projeto
Veja `pom.xml` e a árvore `src/test/java/...` para a organização proposta (Page Objects, clients API, db helpers).

## Dados de teste e pré-condições
- Usuários sugeridos no `sql/seed.sql`.
- Em ambiente compartilhado, usar usernames com prefixo `ci_` ou usar Testcontainers.


## Parte E - SQL

## Respostas:

## Exemplo 1
Propósito: listar admins com >5 logins bem-sucedidos.
Observação: sintaxe OK; garantir GROUP BY por id se username não for único.
Cenário de teste: criar admin com 6 logins bem-sucedidos e validar resultado.

## Exemplo 2
Erro: comparar boolean com string ('false') — corrigir para `WHERE bloqueado = false`.
Teste: inserir true/false e verificar retorno.

## Exemplo 3
Query de status BLOQUEADO quando tentativas >=3.
Teste: setar tentativas=3, validar status e flag `bloqueado` no DB; limpar após teste.

## Exemplo 4
Erro: sintaxe Oracle `(+)` não suportada em Postgres. Reescrever como `LEFT JOIN`.
Postgres retornaria erro de sintaxe.

## Exemplo 5
Propósito: detectar registros órfãos em auditoria_login.
Uso: validar integridade referencial; cenário: deletar usuário e verificar órfãos.
Evitar: FK com ON DELETE CASCADE/RESTRICT, transações atômicas.
