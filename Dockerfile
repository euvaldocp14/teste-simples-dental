# Use a imagem oficial do Maven como a base
FROM maven:3.8.4-openjdk-17 AS builder

# Define o diretório de trabalho no contêiner
WORKDIR /app

# Copia o arquivo pom.xml separadamente para otimizar o cache do Docker
COPY pom.xml .

# Baixa as dependências do Maven (somente se o pom.xml foi alterado)
RUN mvn dependency:go-offline

# Copia os arquivos do código-fonte para o contêiner
COPY src src

# Copia o script wait-for-it.sh para o diretório de trabalho no contêiner
COPY wait-for-it.sh .

# Converte o script para o formato Unix usando sed
RUN sed -i 's/\r//' wait-for-it.sh

# Dá permissões de execução ao script
RUN chmod +x wait-for-it.sh

# Compila o projeto
RUN mvn package

# Agora, use a imagem oficial do OpenJDK para criar uma imagem menor
FROM openjdk:17

# Define o diretório de trabalho no contêiner
WORKDIR /app

# Copia o arquivo JAR construído a partir do estágio anterior
COPY --from=builder /app/target/avaliable-0.0.1-SNAPSHOT.jar .

# Copia também o script wait-for-it.sh para o diretório de trabalho no contêiner
COPY --from=builder /app/wait-for-it.sh .

# Dá permissões de execução ao script no segundo estágio
RUN chmod +x wait-for-it.sh

# Comando para iniciar a aplicação quando o contêiner for iniciado
CMD ["./wait-for-it.sh", "postgres:5432", "--", "java", "-jar", "avaliable-0.0.1-SNAPSHOT.jar"]