# Descrição
API Rest utilizando Spring Boot.

## Dependências e Versões

* Java - v 17
* Spring Boot - v 3.0.6
* Spring Security
* H2 Database
* Swagger (com springdoc)
* Jsonwebtoken

## Endpoints Principais - Autenticação

```bash
/roles (criação de roles)
/auth/signup (criação de usuários)
/auth/signin (login de usuários)
```

## Endpoints Principais - Negócios

```bash
/autores 
/editoras
/livros
/livros/por-editora/{id}
```

## Endpoints - Coleção de todos os Endpoints para Insomnia

```bash
Insomnia_Endpoints_Collection.json
```

## Executando

```bash
mvnw spring-boot:run
```

## Sobre

- Author - [Alexandre Paixão]

## Licença

GNU GPL