#API do Backend do Roteiro de Entrevista Spring boot

Segue a primeira parte do desafio para a entrevista, a parte do Backend em Spring.

Algumas configurações que talvez sejam necessárias:
* Utilizei um banco de Dados Mysql8 local e talvez seja preciso alterar as configurações de DataSource no arquivo src/main/resources/application.properties.

#Rotas
POST /cities - Body { "name": "string", "provincy": { "name": "string" } } - Cadastrar cidade
GET /cities - Lista todas as cidades
POST /cities - Body: { "name": "string" } - Consultar cidade por parte do nome
POST /cities/byProvincyName - Body: { "name": "string" } - Consultar cidade pelo estado


POST /customers - Body { "name": "string", "sex": "Enum(MALE, FEMALE, OTHERS)", "birthDate": "0000-00-00", "age": number, "city": { "id": number } }
GET /customers/id - pathparam {id} : id do cliente - Consultar cliente pelo Id
GET /customers/byName - Body: { "name": "string" } - Consultar Cliente pelo nome
DELETE /customers/id - pathparam {id} : id do cliente - Remover Cliente 
PUT /customers/id - Body: {"name": "string"} - Alterar o nome do cliente
