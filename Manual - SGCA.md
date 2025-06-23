# 📄 Manual de Execução – Sistema de Gestão de Cursos e Alunos - (SGCA)

---

## ✅ Requisitos Mínimos:

* **Java JDK**: Versão 8 ou superior
* **MySQL Server**: Versão 5.x ou 8.x
* **Driver JDBC MySQL (mysql-connector-java)**: Adicionado ao projeto (biblioteca externa)
* **IDE recomendada (opcional)**: IntelliJ IDEA, NetBeans ou Eclipse
* **Arquivo de estilo (CSS)**: Localizado na pasta `/gui/style.css`

---

## ⚙️ Preparação do Banco de Dados (MySQL):

1. **Crie o banco de dados**:

```sql
CREATE DATABASE gestao_cursos;
```

2. **Utilize o banco criado**:

```sql
USE gestao_cursos;
```

3. **Crie as tabelas necessárias**:

```sql
CREATE TABLE curso (
    idCurso INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    cargaHoraria INT NOT NULL,
    limiteAlunos INT NOT NULL,
    ativo TINYINT NOT NULL
);

CREATE TABLE aluno (
    idAluno INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    cpf VARCHAR(11) NOT NULL UNIQUE,
    telefone VARCHAR(20),
    email VARCHAR(100) NOT NULL,
    dataNascimento DATE NOT NULL,
    ativo TINYINT NOT NULL,
    idCurso INT,
    FOREIGN KEY (idCurso) REFERENCES curso(idCurso) ON DELETE CASCADE
);
```

4. **Configuração da conexão com o banco**:
   No arquivo da factory de conexão (`factory/Conexao.java`), configure o seguinte:

```java
private static final String URL = "jdbc:mysql://localhost:3306/gestao_cursos";
private static final String USUARIO = "root";
private static final String SENHA = "SUA_SENHA";
```

> Substitua `"SUA_SENHA"` pela sua senha real do MySQL.

---

## 🖥️ Execução do Sistema:

### Caso esteja usando uma IDE (Eclipse, IntelliJ, NetBeans):

1. Abra o projeto na IDE.
2. Certifique-se de que o driver `mysql-connector-java.jar` está incluído no Build Path ou nas Dependências.
3. Execute a classe principal (`Main.java` ou o menu principal do sistema).

---

### Caso tenha um JAR executável:

1. Navegue até a pasta onde está o arquivo `.jar`.
2. Execute pelo terminal/cmd:

```bash
java -jar SistemaGestaoCursosAlunos.jar
```

---

## 🎨 Aparência da Interface:

* As telas possuem layout em **VBox** e **HBox**, organizadas de forma amigável.
* Os botões, campos de texto, tabelas e mensagens estão com estilos definidos em **CSS** (local: `/gui/style.css`).

> **Se o CSS não carregar:**
> Verifique se o caminho dentro das GUIs está assim:

```java
scene.getStylesheets().add(getClass().getResource("/gui/style.css").toExternalForm());
```

---

## 📋 Funcionalidades Disponíveis:

### ✅ Módulo Curso:

* Cadastrar novo curso
* Editar curso
* Excluir curso (exclui também os alunos vinculados)
* Desabilitar curso
* Reabilitar curso
* Listar cursos ativos e inativos
* Controlar o limite máximo de alunos

### ✅ Módulo Aluno:

* Cadastrar novo aluno
* Editar aluno
* Excluir aluno
* Desabilitar aluno
* Reabilitar aluno
* Listar alunos ativos e inativos
* Validação de CPF, email e idade mínima de 16 anos

### ✅ Relatórios:

* Listar alunos de um curso específico
* Listar todos os cursos com seus respectivos alunos
* Exportar relatórios de alunos ativos por curso (arquivo `.txt`)
* Exportar relatórios de alunos inativos por curso (arquivo `.txt`)

---

## 💾 Exportação de Relatórios:

1. Ao clicar em **Exportar**, o sistema abrirá um diálogo para o usuário escolher o local e o nome do arquivo `.txt`.
2. Os arquivos serão salvos contendo o nome do curso e a lista de alunos vinculados, com nome e CPF.

---

## ❗ Possíveis Erros Comuns:

| Problema                  | Causa                                 | Solução                                                                                                |
| ------------------------- | ------------------------------------- | ------------------------------------------------------------------------------------------------------ |
| Erro de conexão com MySQL | Configuração errada na `Conexao.java` | Verifique URL, usuário e senha                                                                         |
| Tabela não encontrada     | Banco/tabelas ainda não criados       | Execute os scripts SQL informados                                                                      |
| CSS não aplicado          | Caminho incorreto do CSS              | Confirme se o arquivo `style.css` está na pasta `/gui` e que o `.jar` ou projeto IDE está reconhecendo |

---

## 📂 Estrutura de Pastas (Exemplo):

```
src/
├── dao/
├── factory/
├── gui/
├── modelo/
├── Main.java
├── gui/style.css
```

---

## ✅ Finalização:

O sistema está pronto para uso, seguindo todas as regras de negócio e validações exigidas pela professora.

---

Se quiser eu posso te gerar o arquivo `.docx` ou `.pdf` deste manual. Quer?
