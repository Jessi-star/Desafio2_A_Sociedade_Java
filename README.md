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
- **Spring Boot**  
- **OpenFeign**  
- **H2**  
- **Maven**  
