# WorkshopMongoDB
 Exercício Prático: Construção de API RESTful em Java com MongoDB

Este projeto é uma API RESTful desenvolvida em Java usando o framework Spring Boot e MongoDB como banco. O projeto foi criado como um exercício prático para demonstrar a construção de uma API completa com operações CRUD (Create, Read, Update, Delete) e integração com MongoDB. O foco é a simulação de um fórum virtual simplificado, com posts, comentários e seus usuários associados.

## Pré-requisitos
Antes de executar a aplicação, certifique-se de ter os seguintes itens instalados:
- Java 17
- Maven

## Configuração
Configure as credenciais do MongoDB:
Crie um arquivo application.properties dentro do diretório src/main/resources e adicione as configurações do seu MongoDB.

- A aplicação estará disponível em http://localhost:8080.

## Endpoints da API

### Usuário 
1. Criar um Usuário
    Método: POST
    Endpoint: /api/users
    Descrição: Cria um novo usuário.
    Requisição: JSON com dados do usuário.
    Resposta: Dados do usuário criado.
2. Buscar Todos os Usuários
    Método: GET
    Endpoint: /api/users
    Descrição: Retorna uma lista de todos os usuários.
    Resposta: Lista de usuários.
3. Buscar Usuário por ID
    Método: GET
    Endpoint: /api/users/{id}
    Descrição: Retorna os detalhes de um usuário pelo ID.
    Parâmetro: id (ID do usuário)
    Resposta: Dados do usuário.
4. Atualizar um Usuário
    Método: PUT
    Endpoint: /api/users/{id}
    Descrição: Atualiza os dados de um usuário pelo ID.
    Parâmetro: id (ID do usuário)
    Requisição: JSON com os dados atualizados do usuário.
    Resposta: Dados do usuário atualizado.
5. Excluir um Usuário
    Método: DELETE
    Endpoint: /api/users/{id}
    Descrição: Exclui um usuário pelo ID.
    Parâmetro: id (ID do usuário)
    Resposta: Mensagem de sucesso.

### Posts
1. Buscar Todos os Posts
    Método: GET
    Endpoint: /posts
    Descrição: Retorna uma lista de todos os posts disponíveis na base de dados.
    Resposta: Uma lista de objetos Post no formato JSON.

2. Buscar Post por ID
    Método: GET
    Endpoint: /posts/{id}
    Descrição: Retorna um post específico com base no ID fornecido.
    Parâmetros:
    id (Path Variable): ID do post a ser buscado.
    Resposta: Objeto Post no formato JSON.

3. Buscar Posts por Título
    Método: GET
    Endpoint: /posts/titlesearch
    Descrição: Retorna uma lista de posts cujo título contém o texto fornecido.
    Parâmetros:
        text (Query Parameter): Texto do título a ser buscado.
    Resposta: Lista de objetos Post no formato JSON.

4. Busca Completa com Filtros de Data
    Método: GET
    Endpoint: /posts/fullsearch
    Descrição: Retorna uma lista de posts que correspondem ao texto fornecido e ao intervalo de datas especificado.
    Parâmetros:
        text (Query Parameter): Texto a ser buscado no conteúdo dos posts.
        minDate (Query Parameter): Data mínima no formato YYYY-MM-DD para o filtro.
        maxDate (Query Parameter): Data máxima no formato YYYY-MM-DD para o filtro.
    Resposta: Lista de objetos Post no formato JSON.

## Documentação da API
A documentação da API está disponível através do Swagger UI em:

http://localhost:8080/swagger-ui.html