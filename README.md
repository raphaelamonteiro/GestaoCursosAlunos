# Sistema de Gestão de Cursos e Alunos

## 📋 Descrição
Este projeto é um sistema de gestão acadêmica que permite o cadastro e gerenciamento de cursos e alunos, com persistência em banco de dados MySQL e arquivos de texto. Desenvolvido com Java e JavaFX.

---

## 🎯 Funcionalidades

### Curso
- Cadastrar, editar, excluir, desabilitar e reabilitar cursos.
- Listar todos os cursos (ativos e inativos).
- Limitar o número de alunos por curso.
- Listar alunos matriculados em um curso.

### Aluno
- Cadastrar, editar, excluir, desabilitar e reabilitar alunos.
- Associar cada aluno a apenas um curso.
- Validar CPF, nome, email e idade mínima (16 anos).

### Relatórios
- Exportar alunos ativos por curso (arquivo texto).
- Exportar alunos inativos por curso.



## 💾 Persistência de Dados
- Banco de dados MySQL (via JDBC).
- Exportação de relatórios em arquivos `.txt`.

---

## 📁 Estrutura de Pacotes

```

src/
├── factory/    → Conexão com banco
├── modelo/     → Classes Aluno e Curso
├── dao/        → Classes DAO (CRUD)
├── gui/        → Interface gráfica JavaFX
├── util/       → Validações, manipuladores

````


## 🛠️ Tecnologias Utilizadas

- Java 17+
- JavaFX
- MySQL
- JDBC
- Maven (opcional)
- CSS (para estilização)

---

## ⚙️ Como Executar

### Pré-requisitos
- Java JDK 17 ou superior
- MySQL Server
- IDE (Eclipse, IntelliJ, NetBeans)

### Passos

1. Clone o projeto:
   ```bash
   git clone https://github.com/seuusuario/SistemaGestaoCursosAlunos.git
````

2. Execute o script SQL:

   * Local: `database/script.sql`
   * Cria tabelas `curso` e `aluno`.

3. Configure a conexão com MySQL:

   * Arquivo: `factory/ConnectionFactory.java`
   * Adapte `URL`, `usuário` e `senha`.

4. Compile e execute:

   * Rode a classe `MenuPrincipal.java` (JavaFX)

---

## 📄 Scripts SQL

```sql
CREATE DATABASE gestao_academica;

USE gestao_academica;

CREATE TABLE curso (
  id INT PRIMARY KEY AUTO_INCREMENT,
  nome VARCHAR(100) NOT NULL,
  cargaHoraria INT NOT NULL,
  limiteAlunos INT NOT NULL,
  ativo BOOLEAN DEFAULT TRUE
);

CREATE TABLE aluno (
  id INT PRIMARY KEY AUTO_INCREMENT,
  cpf VARCHAR(11) NOT NULL UNIQUE,
  nome VARCHAR(100) NOT NULL,
  email VARCHAR(100) NOT NULL,
  dataNascimento DATE NOT NULL,
  idCurso INT,
  ativo BOOLEAN DEFAULT TRUE,
  FOREIGN KEY (idCurso) REFERENCES curso(id) ON DELETE CASCADE
);
```

---
