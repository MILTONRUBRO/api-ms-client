# Usando a imagem base do JDK 17
FROM openjdk:17-jdk-slim

# Definindo o diretório de trabalho dentro do contêiner
WORKDIR /app

# Copiando o arquivo JAR para o contêiner (com versão especificada)
COPY target/client-api-0.0.1.jar client-api.jar

# Expondo a porta em que a aplicação irá rodar
EXPOSE 8080

# Comando para executar a aplicação
ENTRYPOINT ["java", "-jar", "client-api-0.0.1.jar"]