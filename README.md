# 🚀 NutriReceitas API - Spring Boot

API REST para gerenciamento de receitas culinárias desenvolvida com **Spring Boot** e **PostgreSQL**. Fornece endpoints completos para autenticação de usuários e operações CRUD de receitas.

## 📋 Índice

- [🎯 Funcionalidades](#-funcionalidades)
- [🛠️ Tecnologias](#️-tecnologias)
- [📦 Pré-requisitos](#-pré-requisitos)
- [⚙️ Configuração](#️-configuração)
- [🚀 Como Executar](#-como-executar)
- [🔗 Endpoints da API](#-endpoints-da-api)
- [📊 Banco de Dados](#-banco-de-dados)
- [🧪 Testando a API](#-testando-a-api)
- [🔧 Configurações CORS](#-configurações-cors)

## 🎯 Funcionalidades

- ✅ **Autenticação de usuários** (cadastro e login)
- ✅ **CRUD completo de receitas** por usuário
- ✅ **Validações de dados** com mensagens personalizadas
- ✅ **Integração com PostgreSQL** 
- ✅ **Configuração CORS** para cliente JavaFX
- ✅ **Logs detalhados** para desenvolvimento
- ✅ **Tratamento de erros** com respostas HTTP apropriadas

## 🛠️ Tecnologias

- **Java 17** - Linguagem de programação
- **Spring Boot 3.5.0** - Framework principal
- **Spring Data JPA** - Persistência de dados
- **PostgreSQL** - Banco de dados relacional
- **Jackson** - Serialização JSON
- **Maven** - Gerenciamento de dependências
- **Hibernate** - ORM (Object-Relational Mapping)

## 📦 Pré-requisitos

- **Java 17** ou superior
- **Maven 3.6+**
- **PostgreSQL 12+** instalado e configurado
- **IDE** (IntelliJ IDEA, Eclipse, VS Code)

## ⚙️ Configuração

### 1. PostgreSQL

```sql
-- Criar banco de dados
CREATE DATABASE nutrireceitas;

-- Criar usuário (opcional)
CREATE USER nutriuser WITH PASSWORD '123456';
GRANT ALL PRIVILEGES ON DATABASE nutrireceitas TO nutriuser;
```

### 2. application.properties

```properties
# Configuração do PostgreSQL
spring.datasource.url=jdbc:postgresql://localhost:5432/nutrireceitas
spring.datasource.username=postgres
spring.datasource.password=123456
spring.datasource.driver-class-name=org.postgresql.Driver

# Configuração JPA/Hibernate
spring.jpa.hibernate.ddl-auto=create-drop
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect

# Configuração do servidor
server.port=8080

# CORS
spring.web.cors.allowed-origins=*
spring.web.cors.allowed-methods=GET,POST,PUT,DELETE,OPTIONS
spring.web.cors.allowed-headers=*

# Logs
logging.level.org.hibernate.SQL=DEBUG
logging.level.com.nutrireceitas=DEBUG
```

## 🚀 Como Executar

### Método 1: Maven
```bash
# Clonar o repositório
git clone https://github.com/RGPaix/nutrireceitas-api-main.git
cd nutrireceitas-api-main

# Compilar e executar
mvn clean spring-boot:run
```

### Método 2: JAR
```bash
# Gerar JAR
mvn clean package

# Executar JAR
java -jar target/demo-0.0.1-SNAPSHOT.jar
```

### Verificar se está rodando
```bash
curl http://localhost:8080/usuario
# Deve retornar: []
```

## 🔗 Endpoints da API

### 👤 Usuários

| Método | Endpoint | Descrição | Body |
|--------|----------|-----------|------|
| `GET` | `/usuario` | Listar todos os usuários | - |
| `GET` | `/usuario/id/{id}` | Buscar usuário por ID | - |
| `GET` | `/usuario/nome/{nome}` | Buscar usuário por nome | - |
| `POST` | `/usuario/salvar` | Cadastrar novo usuário | [UsuarioDto](#usuariodto) |
| `POST` | `/usuario/login` | Autenticar usuário | [UsuarioLoginDto](#usuariologindto) |
| `PUT` | `/usuario/id/{id}` | Atualizar usuário | [UsuarioDto](#usuariodto) |
| `DELETE` | `/usuario/id/{id}` | Excluir usuário | - |

### 🍽️ Receitas

| Método | Endpoint | Descrição | Body |
|--------|----------|-----------|------|
| `GET` | `/receita` | Listar todas as receitas | - |
| `GET` | `/receita/id/{id}` | Buscar receita por ID | - |
| `GET` | `/receita/usuario/{usuarioId}` | Buscar receitas por usuário | - |
| `POST` | `/receita/salvar` | Criar nova receita | [ReceitaDto](#receitadto) |
| `PUT` | `/receita/id/{id}` | Atualizar receita | [ReceitaDto](#receitadto) |
| `DELETE` | `/receita/id/{id}` | Excluir receita | - |

### 📋 Modelos de Dados

#### UsuarioDto
```json
{
  "nome": "João Silva",
  "email": "joao@exemplo.com",
  "senha": "123456"
}
```

#### UsuarioLoginDto
```json
{
  "email": "joao@exemplo.com",
  "senha": "123456"
}
```

#### ReceitaDto
```json
{
  "usuarioId": 1,
  "nome": "Bolo de Chocolate",
  "ingredientes": "- 2 xícaras de farinha\n- 1 xícara de açúcar\n- 3 ovos",
  "tempoPreparo": 45,
  "serve": 8,
  "descricao": "1. Misture os ingredientes\n2. Asse por 45 minutos"
}
```

## 📊 Banco de Dados

### Modelo de Dados

```sql
-- Tabela de usuários
CREATE TABLE usuario (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    senha VARCHAR(255) NOT NULL
);

-- Tabela de receitas
CREATE TABLE receita (
    id SERIAL PRIMARY KEY,
    usuario_id INTEGER NOT NULL,
    nome VARCHAR(255) NOT NULL,
    ingredientes TEXT NOT NULL,
    tempo_preparo INTEGER NOT NULL,
    serve INTEGER NOT NULL,
    descricao TEXT NOT NULL,
    FOREIGN KEY (usuario_id) REFERENCES usuario(id)
);
```

### Dados de Teste (Opcional)

Crie o arquivo `src/main/resources/data.sql`:

```sql
-- Usuários de exemplo
INSERT INTO usuario (nome, email, senha) VALUES 
('João Silva', 'joao@nutrireceitas.com', '123456'),
('Maria Santos', 'maria@nutrireceitas.com', '123456');

-- Receitas de exemplo
INSERT INTO receita (usuario_id, nome, ingredientes, tempo_preparo, serve, descricao) VALUES 
(1, 'Bolo de Chocolate', '- 2 xícaras de farinha\n- 1 xícara de açúcar\n- 3 ovos', 45, 8, '1. Misture tudo\n2. Asse por 45min'),
(2, 'Salada Caesar', '- Alface\n- Frango\n- Molho caesar', 20, 4, '1. Corte a alface\n2. Adicione frango\n3. Tempere');
```

## 🧪 Testando a API

### cURL Examples

```bash
# Cadastrar usuário
curl -X POST http://localhost:8080/usuario/salvar \
  -H "Content-Type: application/json" \
  -d '{"nome":"Teste User","email":"teste@exemplo.com","senha":"123456"}'

# Login
curl -X POST http://localhost:8080/usuario/login \
  -H "Content-Type: application/json" \
  -d '{"email":"teste@exemplo.com","senha":"123456"}'

# Criar receita
curl -X POST http://localhost:8080/receita/salvar \
  -H "Content-Type: application/json" \
  -d '{"usuarioId":1,"nome":"Pão de Açúcar","ingredientes":"- Farinha\n- Açúcar","tempoPreparo":30,"serve":4,"descricao":"1. Misturar\n2. Assar"}'

# Listar receitas do usuário
curl http://localhost:8080/receita/usuario/1
```

### Postman Collection

```json
{
  "info": {
    "name": "NutriReceitas API",
    "schema": "https://schema.getpostman.com/json/collection/v2.1.0/collection.json"
  },
  "variable": [
    {
      "key": "base_url",
      "value": "http://localhost:8080"
    }
  ]
}
```

## 🔧 Configurações CORS

A API está configurada para aceitar requisições de qualquer origem. Para produção, configure origins específicos:

```java
@Configuration
public class WebConfig {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:3000", "http://localhost:8081")
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("*");
            }
        };
    }
}
```

## 🐳 Executar com Docker (Opcional)

```dockerfile
FROM openjdk:17-jdk-slim
COPY target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]
```

```bash
# Build da imagem
docker build -t nutrireceitas-api .

# Executar container
docker run -p 8080:8080 nutrireceitas-api
```

## 📝 Logs e Monitoramento

### Visualizar logs em tempo real:
```bash
tail -f logs/spring.log
```

### Health Check:
```bash
curl http://localhost:8080/actuator/health
```

## 🔒 Segurança

> ⚠️ **Importante**: Esta API foi desenvolvida para fins educacionais. Para produção, implemente:
> - Autenticação JWT
> - Criptografia de senhas (BCrypt)
> - Rate limiting
> - Validações mais rigorosas
> - HTTPS obrigatório

## 🤝 Contribuição

1. Faça fork do projeto
2. Crie uma branch: `git checkout -b feature/nova-funcionalidade`
3. Commit suas mudanças: `git commit -am 'Adiciona nova funcionalidade'`
4. Push para a branch: `git push origin feature/nova-funcionalidade`
5. Abra um Pull Request

## 👨‍💻 Autor

**Rodrigo Paix** - [GitHub](https://github.com/RGPaix)

## 📄 Licença

Este projeto está sob a licença MIT - veja o arquivo [LICENSE](LICENSE) para detalhes.

---

⭐ **Se este projeto te ajudou, deixe uma estrela!**
