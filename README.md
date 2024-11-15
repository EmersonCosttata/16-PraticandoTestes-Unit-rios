
# API REST para Gerenciamento de Produtos

Este projeto é uma **API REST** para gerenciar produtos, com funcionalidades CRUD completas, incluindo criar, ler, atualizar e deletar produtos do sistema. A API permite adicionar novos produtos, listar todos os produtos, buscar produtos específicos por ID, atualizar informações de produtos e excluir produtos do banco de dados.

## Requisitos

- **Java 17**: Certifique-se de que o **Java 17** esteja instalado em seu ambiente.
- **Maven**: Este projeto utiliza **Maven** para gerenciar as dependências.
- **Banco de Dados H2**: O projeto está configurado para utilizar o banco de dados em memória H2, ideal para testes e desenvolvimento rápido.

## Configuração do Banco de Dados

No arquivo `src/main/resources/application.properties`, a conexão com o banco de dados H2 já está configurada:

```properties
# Configurações para banco de dados H2
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.h2.console.enabled=true
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

Acesse o console do H2 em `http://localhost:8080/h2-console` e utilize o URL `jdbc:h2:mem:testdb` para visualizar e testar o banco de dados.

---

## Endpoints e Exemplos de Requisições

### 1. **Adicionar Produto**

**Endpoint:** `POST /api/products`

**Exemplo de Requisição (JSON):**
```json
{
  "name": "Produto A",
  "description": "Descrição do Produto A",
  "price": 50.0,
  "quant": 20
}
```

**Resposta Esperada:** Retorna o produto criado com o ID gerado.

---

### 2. **Buscar Produto por ID**

**Endpoint:** `GET /api/products/{id}`

**Exemplo de Requisição:**
```bash
GET /api/products/1
```

**Resposta Esperada:** Retorna o produto com o ID especificado ou status 404 se não encontrado.

---

### 3. **Listar Todos os Produtos**

**Endpoint:** `GET /api/products`

**Exemplo de Requisição:**
```bash
GET /api/products
```

**Resposta Esperada:** Retorna uma lista com todos os produtos registrados no sistema.

---

### 4. **Atualizar Produto**

**Endpoint:** `PUT /api/products/{id}`

**Exemplo de Requisição (JSON):**
```json
{
  "name": "Produto A Atualizado",
  "description": "Descrição Atualizada",
  "price": 60.0,
  "quant": 15
}
```

**Resposta Esperada:** Retorna o produto atualizado com as novas informações.

---

### 5. **Deletar Produto**

**Endpoint:** `DELETE /api/products/{id}`

**Exemplo de Requisição:**
```bash
DELETE /api/products/1
```

**Resposta Esperada:** Retorna o status 204 (No Content) para confirmar a exclusão do produto.

---

## Dependências

Este projeto utiliza as seguintes dependências:

- **Spring Boot 2.x**: Framework principal para a criação da API REST.
- **Spring Data JPA**: Para interagir com o banco de dados.
- **Banco de Dados H2**: Utilizado para armazenamento em memória.
- **Maven**: Para gerenciar dependências e compilar o projeto.

---

### Como Rodar

1. Clone o repositório para sua máquina local:
   ```bash
   git clone https://github.com/seu-usuario/api-gerenciamento-produtos.git
   ```

2. Acesse o diretório do projeto:
   ```bash
   cd api-gerenciamento-produtos
   ```

3. Execute o projeto com o Maven:
   ```bash
   mvn spring-boot:run
   ```

4. A API estará disponível em `http://localhost:8080`.

---

### Acessar o Console H2

Acesse o console H2 em `http://localhost:8080/h2-console`. No campo `JDBC URL`, insira `jdbc:h2:mem:testdb` e clique em "Connect" para visualizar os dados diretamente no banco H2.

---
### Executar os testes da Api

mvn test

Teste disponiveis tbm na psta/test
