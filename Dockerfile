# Utiliza a imagem base Eclipse Temurin JDK 17 com Alpine Linux, que é uma imagem leve do JDK 17
FROM eclipse-temurin:17-jdk-alpine

# Instalação do Maven
ENV MAVEN_VERSION=3.6.3
ENV MAVEN_HOME=/usr/share/maven
ENV PATH="${MAVEN_HOME}/bin:${PATH}"
RUN wget -q "https://archive.apache.org/dist/maven/maven-3/${MAVEN_VERSION}/binaries/apache-maven-${MAVEN_VERSION}-bin.tar.gz" -O /tmp/apache-maven.tar.gz && \
    tar -xzf /tmp/apache-maven.tar.gz -C /usr/share && \
    ln -s "${MAVEN_HOME}/bin/mvn" /usr/bin/mvn && \
    rm -f /tmp/apache-maven.tar.gz

# Instalação do MySQL
RUN apk add --no-cache mysql mysql-client

# Define o diretório de trabalho dentro do contêiner
WORKDIR /app

# Copia os arquivos de configuração e o arquivo POM para o diretório de trabalho no contêiner
COPY pom.xml .
COPY src ./src

# Executa o Maven para compilar e empacotar o projeto
RUN mvn clean package

# Copia o arquivo JAR da sua aplicação para o diretório de trabalho no contêiner
COPY target/blackbox-annotations-0.0.1-SNAPSHOT.jar /app/blackbox-app.jar

# Expor a porta 8090 para a aplicação
EXPOSE 8090

# Expor a porta 3306 para o MySQL (opcional)
EXPOSE 3306

# Define o comando padrão a ser executado quando o contêiner iniciar
ENTRYPOINT ["java", "-jar", "blackbox-app.jar"]
