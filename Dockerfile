# Utiliza a imagem base Eclipse Temurin JDK 17 com Alpine Linux, que é uma imagem leve do JDK 17
FROM eclipse-temurin:17-jdk-alpine
# Define a variável de ambiente JAVA_HOME para apontar para o diretório do JDK
ENV JAVA_HOME=/opt/java/openjdk

# Copia o JDK da imagem base para o caminho especificado em JAVA_HOME
COPY --from=eclipse-temurin:17-jdk-alpine $JAVA_HOME $JAVA_HOME

# Adiciona o diretório bin do JAVA_HOME ao PATH do sistema, para que os comandos Java possam ser executados diretamente
ENV PATH="${JAVA_HOME}/bin:${PATH}"

# Define o diretório de trabalho dentro do contêiner
WORKDIR /app

# Copia o arquivo JAR da sua aplicação para o diretório de trabalho no contêiner
COPY target/blackbox-annotations-0.0.1-SNAPSHOT.jar /app/blackbox-app.jar

# Define o comando padrão a ser executado quando o contêiner iniciar
ENTRYPOINT ["java", "-jar", "blackbox-app.jar"]