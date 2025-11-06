# TaskFlow: Gerenciador de Tarefas

![Java](https://img.shields.io/badge/Java-17%2B-blue?style=for-the-badge&logo=java)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-green?style=for-the-badge&logo=spring)
![Thymeleaf](https://img.shields.io/badge/Thymeleaf-3.1-blueviolet?style=for-the-badge&logo=thymeleaf)
![Bootstrap](https://img.shields.io/badge/Bootstrap-5.3-purple?style=for-the-badge&logo=bootstrap)

<p align="center">
  <img src="https://i.imgur.com/a23hkv4.png" width="700" alt="Screenshot da Home do TaskFlow">
</p>

## 📖 Tabela de Conteúdo

* [Sobre o Projeto](#-sobre-o-projeto)
* [Funcionalidades](#-funcionalidades)
* [Tecnologias Utilizadas](#-tecnologias-utilizadas)
* [Pré-requisitos](#-pré-requisitos)
* [Como Executar](#-como-executar)
* [Futuras Melhorias](#-futuras-melhorias)
* [Autor](#-autor)

---

## 📌 Sobre o Projeto

O **TaskFlow** é uma aplicação Web Fullstack de gerenciamento de tarefas (To-Do List). O projeto foi desenvolvido como parte de estudos em Java Web para aplicar conceitos de desenvolvimento backend com Spring Boot e frontend com Thymeleaf.

A aplicação permite o cadastro, edição, visualização e exclusão de tarefas, bem como o gerenciamento de usuários "responsáveis" por elas.

---

## ✨ Funcionalidades

* **CRUD de Tarefas:**
    * Criar novas tarefas com descrição, data e responsável.
    * Editar tarefas existentes.
    * Excluir tarefas (ou marcar como concluída).
    * Listar todas as tarefas em uma página principal.
* **CRUD de Responsáveis:**
    * Gerenciamento completo de responsáveis (usuários) em um formulário modal.
* **Validação de Dados (Server-Side):**
    * Uso de `Bean Validation` (`@Valid`) para campos obrigatórios, datas e tamanhos.
    * **Validação de Negócio Customizada:** O sistema impede o cadastro de tarefas com descrições (nomes) duplicadas.
* **Tratamento de Erros Profissional:**
    * Uso de `@ControllerAdvice` para capturar exceções globais (como `NullPointerException`) e exibir uma página de erro amigável.
    * Mensagens de erro de validação exibidas dinamicamente no formulário com Thymeleaf.
* **Experiência do Usuário (UX):**
    * **Notificações (Toasts):** Mensagens de feedback de sucesso (ex: "Tarefa salva!") são exibidas após o redirecionamento, utilizando `RedirectAttributes`.
    * Interface responsiva construída com Bootstrap 5.

---

## 💻 Tecnologias Utilizadas

O projeto foi construído utilizando o seguinte stack:

* **Backend:**
    * **Java 17** (ou 21)
    * **Spring Boot:** Framework principal.
    * **Spring Data JPA / Hibernate:** Para persistência de dados.
    * **Spring Web:** Para criar os controllers MVC.
    * **Spring Validation:** Para validação dos dados de entrada.
* **Frontend:**
    * **Thymeleaf:** Motor de templates para renderização server-side.
    * **Bootstrap 5:** Para estilização e componentes de UI (modais, toasts, etc.).
* **Banco de Dados:**
    * **MySQL**.
* **Build/Dependências:**
    * **Maven**.
* **Logs:**
    * Configuração de log (via `application.yml`) para exibir queries SQL (`DEBUG`) e parâmetros (`TRACE`) do Hibernate para depuração.

---

## 🚀 Como Executar

Siga os passos abaixo para executar o projeto em seu ambiente local.

### 📋 Pré-requisitos

Antes de começar, você precisará ter instalado em sua máquina:
* [JDK 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html) (ou superior)
* [Maven 3.x](https://maven.apache.org/download.cgi) (ou superior)
* [Git](https://git-scm.com/downloads) (Opcional, para clonar)
* [MYSQL](https://www.mysql.com/download/)

### ⚙️ Configuração 

1.  **Clone o repositório:**
    ```bash
    git clone https://github.com/Luc033/TaskFlow.git
    cd TaskFlow
    ```

2.  **Configure o Banco de Dados:**
    * Abra o arquivo `src/main/resources/application.yml`
    * Altere as propriedades `spring.datasource.url`, `spring.datasource.username` e `spring.datasource.password` para corresponder às configurações do seu banco de dados local.

    *Exemplo para MYSQL:*
    ```yaml
    spring:
      datasource:
        url: jdbc:mysql://localhost:3306/bd_tasklist?createDatabaseIfNotExist=true
        username: seu_user
        password: seu_password
      jpa:
        hibernate:
          ddl-auto: update # 'update' para dev, 'none' para prod
    ```

### ▶️ Executando a Aplicação

1.  **Pelo terminal (usando Maven):**
    * Navegue até a raiz do projeto e execute:
    ```bash
    mvn spring-boot:run
    ```

2.  **Pela sua IDE (IntelliJ/Eclipse):**
    * Abra o projeto.
    * Encontre a classe principal (com a anotação `@SpringBootApplication`).
    * Clique com o botão direito e selecione "Run".

A aplicação estará disponível em `http://localhost:8080` (ou a porta que você configurou).

## 💡 Futuras Melhorias

Este projeto foi construído como uma base sólida (MVP), e há um grande potencial para expansão. O roadmap planejado para futuras versões inclui:

* **Testes Automatizados:** Implementação de testes unitários (JUnit/Mockito) para os Services e Controllers, e testes de integração para garantir a robustez das APIs e da persistência de dados.
* **Integração e Deploy Contínuo (CI/CD):** Criação de um pipeline automatizado (ex: GitHub Actions) para rodar os testes e fazer o deploy da aplicação em um ambiente de produção (ex: Heroku, AWS, etc.).
* **Sistema de Login e Usuários:** Inclusão de um sistema de autenticação e autorização (Spring Security), permitindo que cada usuário acesse e gerencie apenas suas próprias tarefas.
* **Tarefas Cooperadas:** Permitir que **mais de um responsável** seja atribuído a uma mesma tarefa.
* **Notificações por E-mail:** Envio de notificações (ex: Spring Mail) quando uma tarefa for atribuída, encerrada ou comentada.
* **Ações em Lote:** Adicionar a funcionalidade para **selecionar múltiplas tarefas** na home e aplicar alterações em massa (remover, concluir, etc.).
* **Campos Avançados de Tarefa:**
    * Inclusão de **título e descrição** (atualmente há apenas um campo "descrição").
    * Permitir **formatação de texto** na descrição (Markdown ou Rich Text Editor).
    * Permitir o **anexo de imagens** e a criação de **checklists** (sub-tarefas) dentro de uma tarefa.
    * Informar a data da criação da tarefa e quantos dias faltam para a sua conclusão e avisar quando estiver atrasada.
* **Relatórios:** Geração de relatórios de produtividade (ex: tarefas concluídas por período, por responsável).
* **Gerenciamento de Perfil:**
    * Permitir que o usuário/responsável **altere seus dados** (nome, senha).
    * Adicionar **foto de perfil**.
* **Personalização:** Adicionar a opção de **personalizar o tema** do sistema (ex: Light/Dark mode).

---

## 👨‍💻 Autor

Feito com ❤️ por **Lucas Melo** (Luc Systems).

[Linkedin](https://linkedin.com/in/lucas-melo-dev) - [Github](https://github.com/Luc033)