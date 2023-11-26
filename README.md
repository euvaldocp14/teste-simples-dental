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

   Aguarde até que os conteiners estejam UP. O aplicativo estará disponível em http://localhost:8080.
  
