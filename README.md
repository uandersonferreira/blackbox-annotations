# Projeto Dockerizado com Spring Boot, MySQL e phpMyAdmin

Este projeto é um exemplo de aplicação Spring Boot com um banco
de dados MySQL, gerenciado através do phpMyAdmin.
Tudo é orquestrado com Docker Compose.

## Serviços

O projeto consiste nos seguintes serviços:

- **app**: Aplicação Spring Boot
- **db**: Banco de dados MySQL
- **phpmyadmin**: Interface web para gerenciar o MySQL

## Pré-requisitos

- Docker instalado
- Docker Compose instalado

## Como executar

1. Clone este repositório:

```sh
git clone https://github.com/uandersonferreira/blackbox-annotations.git
cd seu-repositorio
```

2. Construa e inicie os containers:

```sh
docker-compose up --build
```

3. Acesse os serviços conforme descrito abaixo.

### Acesso

- **Aplicação Spring Boot**: [http://localhost:8090](http://localhost:8090)
- **MySQL**: Acessível no host `localhost` e porta `3308`
- **phpMyAdmin**: [http://localhost:8081](http://localhost:8081)

### Credenciais de Acesso

#### MySQL

- **Usuário**: `root`
- **Senha**: `root`

#### phpMyAdmin

- **Usuário**: `root`
- **Senha**: `root`

### Variáveis de Ambiente

As variáveis de ambiente principais usadas na configuração do Docker Compose são:

- `SPRING_PROFILES_ACTIVE=dev`: Ativa o perfil 'dev' para configurações específicas do ambiente de desenvolvimento.
- `SPRING_DATASOURCE_URL=jdbc:mysql://db:3306/mydatabase`: URL do banco de dados MySQL.
- `SPRING_DATASOURCE_USERNAME=root`: Nome de usuário do banco de dados MySQL.
- `SPRING_DATASOURCE_PASSWORD=root`: Senha do banco de dados MySQL.
- `MYSQL_ROOT_PASSWORD=root`: Define a senha do usuário root do MySQL.
- `MYSQL_DATABASE=mydatabase`: Define o nome do banco de dados a ser criado.

## Volumes

- `blackbox_data`: Volume para persistência dos dados do MySQL.

## Encerrando os Serviços

Para encerrar e remover os containers, use:

```sh
docker-compose down
```

Isso encerrará os serviços e removerá os containers criados pelo Docker Compose.

## Problemas Comuns

### Erro ao Obter Credenciais

Se você encontrar o
erro `error getting credentials - err: exit status 1, out: no usernames for https://index.docker.io/v1/`, tente fazer
login no Docker Hub novamente:

```sh
sudo docker login
```

- Informe seu username, tudo minúsculo
- Sua senha do dockerHub

[Obs]: Certifique-se de ajustar o conteúdo de acordo com os detalhes
específicos do seu projeto, como o URL do repositório e quaisquer
outras informações relevantes.