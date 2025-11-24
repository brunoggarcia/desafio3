# Viagens API — Spring Boot 3 & PostgreSQL

API RESTful para gerenciamento de viagens, desenvolvida com **Spring Boot 3**, **Spring Data JPA**, **Spring Security** e **PostgreSQL**.  
Possui autenticação e controle de acesso baseado em função (**RBAC**) para proteger endpoints conforme permissões.

---

## Requisitos

Antes de iniciar, certifique-se de ter instalado:

- **Java 17+**
- **Apache Maven**
- **PostgreSQL**
- Cliente HTTP (Postman, Insomnia, Thunder Client etc.)

---

---

## Git Clone
Para clonar este repositório e iniciar o projeto localmente, utilize:

```
git clone https://github.com/brunoggarcia/desafio3
```

## Configuração do Banco de Dados

1. Crie o banco de dados:

```
CREATE DATABASE viagens_db;
```

2. Ajuste o arquivo `src/main/resources/application.properties`:

```
spring.datasource.url=jdbc:postgresql://localhost:5433/viagens_db
spring.datasource.username=postgres
spring.datasource.password=123
```

---

## Rodando a Aplicação

No terminal, dentro do diretório do projeto (onde está o `pom.xml`):

### 1. Vá até ao diretório do programa
```
cd desafio3
```

### 2. Instalar dependências e compilar

```
mvn clean install
```

### 3. Iniciar a aplicação

```
mvn spring-boot:run
```

---

### Usuários de Teste

Para testar a API via Postman, Insomnia ou outro cliente HTTP, use **Basic Auth** com os seguintes usuários:

| Role    | E-mail                        | Senha  | Permissão |
|---------|-------------------------------|--------|------------|
| ADMIN   | admin@viagens.com             | 123456 | Acesso total a todos os endpoints |
| CLIENTE | cliente@viagens.com           | 123456 | Listar destinos, criar reservas, avaliar destinos |

> No Postman, selecione **Authorization → Basic Auth** e insira e-mail e senha correspondentes.


### Exemplo de Destino para Testes

Use esse JSON ao criar um destino via **POST `/destinos`**:

```json
{
    "nome": "Praia de Itaparica",
    "localizacao": "Bahia, Brasil",
    "descricao": "Águas cristalinas e tranquilas.",
    "precoPacote": 1500.0
}
```

---

## Permissões por Endpoint

| Endpoint                        | Método | Descrição                             | Permissão      | Exemplo de Uso |
|---------------------------------|--------|-----------------------------------------|----------------|----------------|
| `/destinos`                     | POST   | Criar novos destinos                    | ADMIN          | `POST /destinos` |
| `/destinos/{id}`                | DELETE | Excluir um destino específico           | ADMIN          | `DELETE /destinos/1` |
| `/destinos/{id}`                | GET    | Buscar destino pelo ID                  | CLIENTE, ADMIN | `GET /destinos/5` |
| `/destinos/pesquisar={nome}`    | GET    | Pesquisar destinos por nome             | CLIENTE, ADMIN | `GET /destinos/pesquisar?termo=itaparica` |
| `/destinos/pesquisar={local}`   | GET    | Pesquisar destinos por local            | CLIENTE, ADMIN | `GET /destinos/pesquisar?termo=bahia` |
| `/destinos/{id}/avaliar`        | POST   | Enviar avaliação                        | CLIENTE, ADMIN | `POST /destinos/3/avaliar?nota=5` |
| `/reservas/{id_destino}`        | POST   | Criar/gerenciar reservas                | CLIENTE, ADMIN | `POST /reservas/2` |

