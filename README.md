# Desafio 2 - Microsserviços com Integração à API JSONPlaceholder

## 📚 Descrição do Projeto

Este projeto foi desenvolvido como parte de um **desafio técnico em grupo** para criar uma aplicação baseada em **microsserviços** que consome dados da API JSONPlaceholder ([https://jsonplaceholder.typicode.com](https://jsonplaceholder.typicode.com)). O objetivo é desenvolver dois microsserviços independentes que se comunicam entre si, permitindo operações de CRUD em posts.

---

### 👥 Equipe
- **Emily L. Balestrin**  
- **Felipe Machado Vidal**  
- **Jessica Abreu Rodrigues**  
- **Nata Cezer Bordignon**  
- **Vitor Hugo Balke Nodari**
  
### 🏗️ Arquitetura dos Microsserviços

1. **Microsserviço A** (`porta 8080`):  
   - Ponto de entrada da aplicação.  
   - Interage com o Microsserviço B para oferecer endpoints ao usuário.  
   - Exposição de endpoints REST para realizar operações como consultar, criar, atualizar e excluir posts.

2. **Microsserviço B** (`porta 8081`):  
   - Integra diretamente com a API JSONPlaceholder para consumo do recurso `/posts`.  
   - Encapsula a lógica de comunicação com a API externa, garantindo uma abstração para o Microsserviço A.  
   - Armazena e manipula os dados localmente utilizando **H2**.

---

## ⚙️ Funcionalidades

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

## 💻 Requisitos Técnicos

### Tecnologias Utilizadas
- **Java 17**  
- **Spring Boot 3.4.0**  
- **Spring Cloud OpenFeign**  
- **H2 Database**  
- **Hibernate Validator**  
- **Lombok**  
- **API REST**
- **Maven**

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
```

---

## 📡 Endpoints da API

  ### Microsserviço A
| Método | Endpoint             | Descrição                               |
|--------|----------------------|-----------------------------------------|
| GET    | `/posts`             | Buscar todos os posts                   |
| GET    | `/posts/{id}`        | Buscar um post por ID                   |
| POST   | `/posts`             | Criar um novo post                      |
| PUT    | `/posts/{id}`        | Atualizar um post existente             |
| DELETE | `/posts/{id}`        | Excluir um post por ID                  |

### Microsserviço B
| Método | Endpoint             | Descrição                               |
|--------|----------------------|-----------------------------------------|
| GET    | `/api/posts`         | Buscar todos os posts                   |
| GET    | `/api/posts/{id}`    | Buscar um post por ID                   |
| POST   | `/api/posts`         | Criar um novo post                      |
| PUT    | `/api/posts/{id}`    | Atualizar um post existente             |
| DELETE | `/api/posts/{id}`    | Excluir um post por ID                  |
| POST   | `/api/sync-data`     | Sincronizar posts externos              |

---

## 🧪 Executar Testes

### Para o Microsserviço A
```bash
cd MicroServiceA
mvn test
```

### Para o Microsserviço B
```bash
cd MicroServiceB
mvn test
```
---

## 📝 Dificuldades e Experiências

---

Este foi o nosso primeiro desafio em grupo, o que trouxe muitos aprendizados. Para a maioria de nós, foi a primeira vez versionando o código de maneira conjunta, e isso gerou algumas experiências tanto boas quanto desafiadoras. 
A integração com o OpenFeign foi um grande passo, já que foi a primeira vez que o utilizamos e, apesar de termos os conhecimentos adquiridos em Spring Boot, a implementação do OpenFeign nos causou um certo desconforto inicial. 
Foi necessário buscar informações adicionais sobre como utilizá-lo corretamente, o que acabou sendo uma oportunidade de aprendizado.

No entanto, apesar dos desafios, conseguimos cumprir o objetivo do projeto. Foi uma grande experiência, e mesmo sendo nosso primeiro trabalho em grupo, conseguimos trabalhar muito bem como equipe, nos apoiando mutuamente e trocando conhecimentos ao longo do processo. 
A colaboração e o esforço conjunto fizeram esse desafio ser bem-sucedido.

---

## 🚀 Melhorias Futuras
- Implementar o CRUD de **comentários** e integrá-lo na API, assim como o CRUD de posts.  
- Melhorar a documentação da API, tornando-a mais clara e acessível.
- Implementar autenticação simples (ex.: autenticação básica ou JWT).
- Melhorar a interação com o usuário, incluindo mensagens de erro mais detalhadas.
- Realizar testes mais enxutos e cobrir os cenários de maneira mais eficiente.  




