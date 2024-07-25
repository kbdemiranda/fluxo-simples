# Usar a imagem oficial do Amazon Corretto
FROM amazoncorretto:17.0.12-alpine3.20

# Informações sobre o mantenedor
LABEL maintainer="kbdemiranda@hotmail.com"

# Defina o diretório de trabalho no container
WORKDIR /app

# Copiar o JAR para o container
COPY ./target/fluxo-0.0.1-SNAPSHOT.jar /app/app.jar

# Executar o JAR
ENTRYPOINT ["java", "-jar", "/app/app.jar"]

# Expor a porta 8080 para acessar a aplicação
EXPOSE 8080
