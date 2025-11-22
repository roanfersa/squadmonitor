## Monitor de Bem-Estar do Time Scrum (Console / Java 17)

Sistema em Java 17 (Maven) que simula, via console, o monitoramento de bem‑estar do time Scrum durante a sprint, com check-ins emocionais diários, identificação de padrões de estresse por regras simples de “IA”, dashboards agregados para o Gestor/Scrum Master e alertas de risco de burnout.

### Equipe do projeto

- Roan Ferreira  
- Eliseu Garcia  
- Beatriz Lima  
- Henrique Pacifico  
- Mary  

### 1. Requisitos

- **Java**: 17 ou superior instalado e configurado no `PATH`.
- **Maven**: 3.x instalado (`mvn -v` deve funcionar no terminal).

### 2. Como compilar

Na pasta do projeto (`GlobalSolutions`):

```bash
mvn clean compile
```

### 3. Como executar (console)

Na raiz do projeto:

```bash
mvn exec:java -Dexec.mainClass=\"br.com.globalsolutions.wellbeing.menu.MainApplication\"
```

Caso o plugin `exec-maven-plugin` não esteja configurado, você também pode compilar e executar manualmente pela IDE, apontando a classe `MainApplication` como classe `main`.

### 4. Usuários de exemplo (seed)

O sistema cria alguns dados em memória na inicialização (`DataSeeder`):

- **Gestor/Scrum Master**  
  - E-mail: `gestor@empresa.com`  
  - Senha: `123`

- **Funcionários (membros de squad)**  
  - `dev1@empresa.com` / senha `123`  
  - `qa1@empresa.com` / senha `123`

Ao fazer login com esses usuários, você verá menus diferentes conforme o perfil.

### 5. Menus disponíveis

- **Menu inicial**
  - Login.
  - Cadastro rápido de funcionário (cria um usuário do tipo `Funcionario` associado ao primeiro departamento/squad de exemplo).

- **Menu Funcionário**
  - Realizar check-in emocional diário (estilo “Weather Report”):
    - Humor geral, energia, estresse, foco, sono e carga de trabalho (escala 1–5).
    - Sentimento dominante e comentário opcional.
  - Visualizar meus check-ins (lista por data/hora, apenas do próprio usuário).

- **Menu Gestor**
  - Visualizar dashboard de um squad (apenas dados agregados):
    - Média de humor geral.
    - Tendência de estresse (comparação de períodos recentes).
    - Total de check-ins e quantidade de riscos ativos.
  - Ver alertas de risco emocional dos squads sob sua responsabilidade.
  - Gerar relatório de clima do time (resumo por squad + recomendação da IA).
  - Realizar o próprio check-in emocional (como qualquer usuário).
  - Visualizar meus check-ins (histórico dos check-ins do próprio gestor).
  - Gerar dados de simulação (cria vários check-ins artificiais para os membros dos squads, facilitando a visualização de riscos, alertas e recomendações da IA).

### 6. IA e regras de risco (simples)

A “IA” é implementada via regras fixas na classe `IAService`:

- **Padrão de estresse alto**:  
  - Se a média de `estresse` for **≥ 4** nos últimos dias, gera um alerta de nível **ALTO**.
- **Risco de burnout**:  
  - Se em pelo menos 3 check-ins recentes a combinação `energia <= 2` e `sono <= 2` ocorrer, gera alerta **CRÍTICO** de risco de burnout.
- **Recomendações de clima**:
  - Estresse médio alto → recomenda retrospectiva focada em carga de trabalho/WIP.
  - Humor médio muito baixo → recomenda 1:1 e ações de team building.
  - Caso contrário → recomenda manter cadência e seguir monitorando.

### 7. Privacidade e agregação

O dashboard e o relatório de clima para o Gestor exibem **apenas indicadores agregados do squad**, sem mostrar o detalhe de check-ins individuais associados a nomes de funcionários.

### 8. Estrutura de pacotes principais

- `br.com.globalsolutions.wellbeing.model`  
  Entidades: `Usuario` (abstrata), `Funcionario`, `Gestor`, `CheckinEmocional`, `Squad`, `Dashboard`, `Alerta`, `Sprint`, `Release`, `Departamento`, `NivelAlerta`.
- `br.com.globalsolutions.wellbeing.repository`  
  Repositórios em memória (listas estáticas): `UsuarioRepository`, `CheckinRepository`, `SquadRepository`, `AlertaRepository`, `DepartamentoRepository`, `SprintRepository`, `ReleaseRepository`.
- `br.com.globalsolutions.wellbeing.service`  
  Serviços de regra de negócio: `UsuarioService`, `CheckinService`, `IAService`, `DashboardService`, `AlertaService`, `SquadService`, `DepartamentoService`, `SprintReleaseService`.
- `br.com.globalsolutions.wellbeing.menu`  
  Menus de console: `MainApplication`, `MenuFuncionario`, `MenuGestor`.
- `br.com.globalsolutions.wellbeing.util`  
  Utilitários: `DataSeeder` (dados de exemplo) e `InputUtil` (leitura segura pelo console).

### 9. Banco de dados (script .sql)

O projeto inclui o arquivo `db_sprint_mind.sql` com um **script SQL de criação das tabelas** do domínio (usuário, funcionário, gestor, squads, sprints, releases, departamentos, check-ins, dashboards e alertas).  
No contexto deste projeto de console, os dados são simulados apenas em memória, mas o script pode ser usado como base para uma futura persistência real em MySQL.

### 10. Diagramas (UML e ER)

Na pasta `docs` estão:

- `uml-class-diagram.puml`: diagrama de classes Java (PlantUML) representando as entidades principais (`Usuario`, `Funcionario`, `Gestor`, `Squad`, `CheckinEmocional`, `Dashboard`, `Alerta`, `Sprint`, `Release`, `Departamento` e relacionamentos).
- `er-diagram.puml`: diagrama entidade-relacionamento do banco de dados, compatível com o script `db_sprint_mind.sql`.

Esses arquivos podem ser abertos em qualquer ferramenta compatível com PlantUML (extensão de IDE, site de visualização, etc.) e exportados como imagem para entrega final.


# squadmonitor # se já estiver com conteúdo, pode pular
