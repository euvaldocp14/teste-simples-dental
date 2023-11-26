# API Avaliação Simples Dental

API Rest para controle de Profissionais e Contatos

## Pré-requisitos

- Java 17
- Maven
- Banco de dados (PostgreSQL)
- Docker

1. Clone o repositório:

   git clone https://github.com/euvaldocp14/teste-simples-dental.git

2. Configure as propriedades do banco de dados no arquivo application.yml.

     url: jdbc:postgresql://localhost:5432/postgres?createDatabaseIfNotExist=true<br />
     username: postgres<br />
     password: root

   Obs.: Substitua os valores conforme necessário.

## Execução
   Execute o seguinte comando, no terminal:<br />
   docker-compose up -d

   Aguarde até que os conteiners estejam UP. O aplicativo estará disponível em http://localhost:8080/swagger-ui/index.html.
   
## Uso
   Foi implementado a parte de segurança da aplicação. Com isso, será necessário gerar o token de serviço, para que possa utilizar os endpoints do projeto. Para ambiente de teste, no endpoint POST
/avaliacao/v1/autenticacao/login, utilize os seguintes dados: <br /><br />
   **Login**: teste <br />
  **Senha**: 123<br />

  Para os endpoints que buscam uma lista de contatos e profissionais, caso queira personalizar o response, segue os campos a serem inseridos:<br />

  | Profissional  | Contato           |
  | ------------- | -------------     |
  | nome          | nome              |
  | ativo         | contato           |
  | cargo         | dataCriacao       |
  | dataCriacao   | nomeProfissional  |
  | dataNascimento|                   |
 
  
  
