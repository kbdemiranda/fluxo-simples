# Desafio Backend

Este projeto visa desenvolver uma API REST para gerenciar um sistema de contas a pagar. A API permitirá realizar operações CRUD em contas a pagar, alterar o status das contas quando o pagamento for efetuado, obter informações detalhadas sobre as contas cadastradas e importar um lote de contas a partir de um arquivo CSV.

## Requisitos Gerais

Para garantir a entrega do projeto os seguintes requisitos foram estabelecidos:

1. **Linguagem de Programação**: Utilizar Java, versão 17 ou superio;
2. **Framework**: Utilizar Spring Boot;
3. **Banco de Dados**: Utilizar PostgreSQL;
4. **Containerização**: A aplicação deve ser executada em um container Docker para garantir portabilidade e facilidade de deploy.
5. **Orquestração**: Utilizar Docker Compose para orquestrar os containers da aplicação e do banco de dados, garantindo que todos os serviços necessários sejam executados corretamente.
6. **Controle de Versão**: O código do projeto deve ser versionado e hospedado em plataformas como GitHub ou GitLab, facilitando o controle de versões e a colaboração.
7. **Autenticação**: Implementar um mecanismo de autenticação para proteger os endpoints da API.
8. **Design de Software**: Organizar o projeto seguindo os princípios do Domain Driven Design (DDD) para garantir a clareza e a manutenção do código.
9. **Migração de Banco de Dados**: Utilizar Flyway para versionamento e migração de banco de dados, permitindo o controle e aplicação de mudanças no esquema de dados.
10. **Persistência**: Utilizar JPA (Java Persistence API) para o mapeamento objeto-relacional, facilitando a interação com o banco de dados.

## Requisitos Específicos

1. **Modelagem de Dados**: Criar uma tabela no banco de dados para armazenar as contas a pagar. A tabela deve conter os seguintes campos:
    - **id**: Identificador único da conta.
    - **data_vencimento**: Data de vencimento da conta.
    - **data_pagamento**: Data de pagamento da conta (pode ser nula).
    - **valor**: Valor da conta.
    - **descricao**: Descrição detalhada da conta.
    - **situacao**: Situação da conta (paga ou não paga).

2. **Entidade Conta**: Implementar a entidade "Conta" na aplicação de acordo com a tabela criada, utilizando JPA para mapeamento objeto-relacional.

3. **Endpoints da API**: Implementar os seguintes endpoints para a API:
    - **POST /faturas**: Cadastrar uma nova conta.
    - **PUT /faturas/{id}**: Atualizar uma conta existente.
    - **PATCH /faturas/{id}/pagar**: Alterar a situação da conta para paga.
    - **PATCH /faturas/{id}/cancelar-pagamento**: Cancelar o pagamento de uma conta.
    - **GET /faturas**: Obter uma lista de contas a pagar, com filtro por data de vencimento e descrição.
    - **GET /faturas/{id}**: Obter detalhes de uma conta específica.
    - **GET /faturas/total-pago**: Obter o valor total pago em um período específico.

4. **Importação de Dados**: Implementar um mecanismo para importar contas a pagar a partir de um arquivo CSV via API.

5. **Testes Unitários**: Implementar testes unitários para garantir a funcionalidade correta dos serviços da aplicação.

## Tecnologias Utilizadas

- Java 17
- Spring Boot 3.2.2
- Spring Data JPA
- Spring Security
- PostgreSQL
- Docker
- Docker Compose
- Flyway
- JUnit 5
- Mockito

## Endpoints

### Autenticação

- **POST /auth/login**
    - Autentica um usuário e retorna um token JWT.
    - Exemplo de request body:
      ```json
      {
        "nomeUsuario": "user",
        "senha": "password"
      }
      ```

### Usuários

----
Para poder testar aplicação, alguns usuarios foram carregados nas migrations iniciais.
Abaixo segue a lista de usuários e suas respectivas credenciais:

| Usuario      | Senha   | Perfil             |
|--------------|---------|--------------------|
| admin        | admin   | Administrador      |
| johndoe      | doe2024 | Contabil           |
| janesmith    | jane123 | Gerente            |
| robertbrown  | bob123  | Usuario (SIMPLES)  |

----
- **POST /usuarios/cadastrar**
    - Cadastra um novo usuário.
    - Exemplo de request body:
      ```json
      {
        "nomeUsuario": "novoUsuario",
        "senha": "senha123",
        "ativo": true
      }
      ```

- **PUT /usuarios/{id}**
    - Atualiza um usuário existente.
    - Exemplo de request body:
      ```json
      {
        "nomeUsuario": "usuarioAtualizado",
        "senha": "novaSenha123",
        "ativo": true
      }
      ```

- **DELETE /usuarios/{id}**
    - Exclui um usuário existente.

- **GET /usuarios**
    - Lista todos os usuários com paginação.

- **GET /usuarios/{id}**
    - Obtém um usuário pelo ID.

### Faturas

- **POST /faturas**
    - Cadastra uma nova fatura.
    - Exemplo de request body:
      ```json
      {
        "descricao": "Compra de papel",
        "valor": 150.00,
        "dataVencimento": "2024-08-01"
      }
      ```

- **PUT /faturas/{id}**
    - Atualiza uma fatura existente.
    - Exemplo de request body:
      ```json
      {
        "descricao": "Compra de tinta",
        "valor": 200.00,
        "dataVencimento": "2024-08-15"
      }
      ```

- **DELETE /faturas/{id}**
    - Exclui uma fatura existente.

- **GET /faturas**
    - Lista todas as faturas com filtros opcionais de descrição, data de vencimento e data de pagamento, e com paginação.

- **GET /faturas/{id}**
    - Obtém uma fatura pelo ID.

- **GET /faturas/total-pago**
    - Obtém o valor total pago por um período.
    - Parâmetros de query: `startDate` e `endDate`.

- **PATCH /faturas/{id}/pagar**
    - Marca uma fatura como paga.

- **PATCH /faturas/{id}/cancelar-pagamento**
    - Cancela o pagamento de uma fatura.

- **POST /faturas/import**
    - Importa um lote de faturas a partir de um arquivo CSV.
    - Parâmetro de formulário: `file`.

## Executando o Projeto

Para executar o projeto, siga os passos abaixo:

1. Certifique-se de ter o Docker e o Docker Compose instalados em sua máquina.

2. Clone o repositório do projeto:
   ```bash
   git clone https://github.com/kbdemiranda/fluxo-simples.git
   cd fluxo-simples
   ```

3. Construa as imagens Docker:
   ```bash
   docker-compose build
   ```

4. Inicie os containers:
   ```bash
   docker-compose up
   ```

5. A aplicação estará disponível em `http://localhost:8080`.