# Desafio 2 - Microsserviços com Integração à API JSONPlaceholder

## Descrição do Projeto

Este projeto foi desenvolvido como parte de um **desafio técnico em grupo** para criar uma aplicação baseada em **microsserviços** que consome dados da API JSONPlaceholder ([https://jsonplaceholder.typicode.com](https://jsonplaceholder.typicode.com)). O objetivo é desenvolver dois microsserviços independentes que se comunicam entre si, permitindo operações de CRUD em posts.
### Equipe
- **Emily L. Balestrin**  
- **Felipe Machado Vidal**  
- **Jessica Abreu Rodrigues**  
- **Nata Cezer Bordignon**  
- **Vitor Hugo Balke Nodari**
  
### Arquitetura dos Microsserviços

1. **Microsserviço A** (`porta 8080`):  
   - Ponto de entrada da aplicação.  
   - Interage com o Microsserviço B para oferecer endpoints ao usuário.  
   - Exposição de endpoints REST para realizar operações como consultar, criar, atualizar e excluir posts.

2. **Microsserviço B** (`porta 8081`):  
   - Integra diretamente com a API JSONPlaceholder para consumo do recurso `/posts`.  
   - Encapsula a lógica de comunicação com a API externa, garantindo uma abstração para o Microsserviço A.  
   - Armazena e manipula os dados localmente utilizando **H2**.

---

## Funcionalidades

### Microsserviço A
1. **Consultar Posts:** Obter todos os posts via Microsserviço B.  
2. **Criar Post:** Simular a criação de um novo post.  
3. **Atualizar Post:** Atualizar posts existentes.  
4. **Excluir Post:** Remover posts existentes.  

### Microsserviço B
1. **Integração com JSONPlaceholder:**  
   - Utiliza **OpenFeign** para consumir o endpoints `/posts` da API JSONPlaceholder.  
2. **Armazenamento Local:**  
   - Manipulação e armazenamento de dados em **H2**.  
3. **Encapsulamento da Lógica:**  
   - Gerencia a lógica de negócio, validações e formatações necessárias antes de enviar os dados ao Microsserviço A.

---

## Requisitos Técnicos

### Tecnologias Utilizadas
- **Java 17**  
- **Spring Boot 3.4.0**  
- **Spring Cloud OpenFeign**  
- **H2 Database**  
- **Hibernate Validator**  
- **Lombok**  
- **API REST**
- -**Maven**


## **Estrutura do Projeto**  

MicroServiceA/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com.sociedadejava.microservicea/
│   │   │       ├── controller/      # Controladores REST
│   │   │       ├── service/         # Lógica de negócios
│   │   │       ├── intraclient/      # Clientes Feign para B
│   │   │       ├── dto/             # Objetos de Transferência de Dados
│   │   │       └── MicroServiceAApplication.java # Classe principal
│   │   └── resources/
│   │       ├── application.properties      # Arquivo de configuração
│   │
└── test/                            # Testes unitários e de integração

MicroServiceB/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com.sociedadejava.microserviceb/
│   │   │       ├── client/          # Clientes Feign para API externa
│   │   │       ├── controller/      # Controladores REST
│   │   │       ├── service/         # Lógica de negócios
│   │   │       ├── repository/      # Repositórios JPA
│   │   │       ├── dto/             # Objetos de Transferência de Dados
│   │   │       ├── entity/          # Entidades do banco de dados
│   │  │        ├── exceptions/     # Exceções
│   │   │       └── MicroServiceBApplication.java # Classe principal
│   │   └── resources/
│   │       ├── application.properties       # Arquivo de configuração
│   │  
└── test/                                    # Testes unitários e de integração


## 🔧 Configuração  


### **Clonar os Repositórios e Iniciar os Microsserviços**
```bash
# Clonar os repositórios
git clone https://github.com/<seu-usuario>/MicroServiceA.git
git clone https://github.com/<seu-usuario>/MicroServiceB.git

# Iniciar o MicroService B
cd MicroServiceB
mvn spring-boot:run

# Iniciar o MicroService A
cd ../MicroServiceA
mvn spring-boot:run


