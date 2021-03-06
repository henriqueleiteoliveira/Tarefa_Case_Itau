# Descrição do projeto

Este projeto armazena tarefas genéricas, sendo possível acompanhar o status atual da tarefa, concluir a tarefa e excluir caso seja necessário.
É possível também listar os status para saber quais são seus respectivos IDs.

## Tecnologias

Foi utilizada a linguagem Java com Spring Boot, e para o banco foi utilizado o MySQL.

## Como foi feito?

Foram feitas duas tabelas para o projeto, tarefa e status, sendo a tabela de status como a tabela de dominio, e a tabela de tarefa contendo todas as informações relacionadas as tarefas. Entre as duas existe uma ligação de chave estrangeira.

Na aplicação foram feitos 4 DTO (Data Transfer Object), sendo dois para representar as tabelas, um contendo as métricas relacionadas aos status, este DTO de métricas herda o DTO do status, e um DTO genérico, que é utilizado nos dados de retorno da aplicação.

O RetornoDTO pode retornar um booleano caso tenha sucesso, uma lista com o dados retornados, ou uma mensagem contendo algum erro na aplicação ou de negócio, que possa ocorrer no processo.

Existem 3 camadas na aplicação: WebService, Facade, DAO. O WebService recebe as requisições Rest, a Facade faz todo o tratamento de regra de negócio, e a DAO faz as chamadas para o banco de dados.

## Como utilizar a aplicação?

Para facilitar o teste, a aplicação está rodando em um servidor AWS, podendo ser utilizada pelas chamadas descritas na collection abaixo.

Na pasta **utils** no repositório há um arquivo .json, que é uma collection de chamadas já contendo todas as chamadas disponíveis na API com exemplo de dados de entrada. Ela já está direcionada para a aplicação no servidor AWS pela variável **{{awsUrl}}**, caso coloque a aplicação localmente, basta alterar para a variável **{{url}}** na url da chamada.

O Projeto está no repositório do github, basta baixar e importar como um projeto maven. Para o banco existe um script de criação de database e tabelas, e os insert dos status, ele está na pasta **utils**. Também será necessário criar um usuário no banco de acordo com o usuário apontado na aplicação.


## Descrição dos Serviços

O endereço da aplicação aws é *http://ec2-3-85-162-183.compute-1.amazonaws.com:8080*

A aqui está um exemplo do serviço [Listar Status](http://ec2-3-85-162-183.compute-1.amazonaws.com:8080/tarefas/listar-status).

O formato padrão das chamadas é {{url}}/tarefas/{{caminho-serviço}}.

Os serviços do tipo GET receberam parâmetros pela url como visto nas chamadas do arquivo collection.json, mas caso não seja passado o parâmetro, retornará todos.

### Serviços:
- **POST** /criar-status
- **GET** /listar-status
- **POST** /criar-tarefa
- **GET** /listar-tarefa
- **PUT** /concluir-tarefa
- **DELETE** /deletar-tarefa
- **GET** /listar-volume-tarefa