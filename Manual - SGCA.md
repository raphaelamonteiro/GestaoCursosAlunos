# 📖 Manual Básico de Instalação e Execução

**Sistema de Gestão de Cursos e Alunos (SGCA) - JavaFX + MySQL**

---

## ✅ Requisitos do Sistema

* **Java JDK 17 ou superior**
* **JavaFX SDK (exemplo: JavaFX 21)**
* **MySQL Server** instalado e em execução
* **IDE recomendada:** IntelliJ IDEA ou Eclipse
* **Driver JDBC MySQL:** `mysql-connector-java`

---

## ✅ Passo 1: Instalar e Configurar o MySQL

1. **Instale o MySQL Server** (caso ainda não tenha).
   Exemplo de usuário padrão:

   * Usuário: `root`
   * Senha: *(defina a sua senha)*

2. **Abra o MySQL Workbench ou outro cliente MySQL**.

3. **Execute o script de criação do banco de dados:**

```sql
-- Criação do Banco
CREATE DATABASE IF NOT EXISTS GestaoCursosAlunos;
USE GestaoCursosAlunos;

-- Tabela Curso
CREATE TABLE curso (
    idCurso INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    cargaHoraria INT NOT NULL,
    limiteAlunos INT NOT NULL,
    ativo BOOLEAN DEFAULT TRUE,
    CONSTRAINT chk_nome_curso CHECK (LENGTH(nome) >= 3),
    CONSTRAINT chk_carga_horaria CHECK (cargaHoraria >= 20),
    CONSTRAINT chk_limite_alunos CHECK (limiteAlunos >= 1)
);

-- Tabela Aluno
CREATE TABLE aluno (
    idAluno INT AUTO_INCREMENT PRIMARY KEY,
    cpf VARCHAR(11) UNIQUE NOT NULL,
    nome VARCHAR(100) NOT NULL,
    telefone VARCHAR(20),
    email VARCHAR(100) NOT NULL,
    dataNascimento DATE NOT NULL,
    curso INT NOT NULL,
    ativo BOOLEAN DEFAULT TRUE,
    CONSTRAINT chk_nome_aluno CHECK (LENGTH(nome) >= 3),
    CONSTRAINT chk_cpf CHECK (LENGTH(cpf) = 11),
    CONSTRAINT chk_email CHECK (email REGEXP '^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$'),
    CONSTRAINT fk_aluno_curso FOREIGN KEY (curso) REFERENCES curso(idCurso) ON DELETE CASCADE
);

-- Inserção de Cursos Exemplo
INSERT INTO curso (nome, cargaHoraria, limiteAlunos) VALUES 
('Análise e Desenvolvimento de Sistemas', 2400, 30),
('Engenharia de Software', 2000, 25),
('Ciências de Dados', 2000, 25),
('Redes de Computadores', 2400, 20),
('Ciência da Computação', 3200, 20);
```

---

## ✅ Passo 2: Configurar o Projeto JavaFX

### Estrutura de Pastas Recomendada:

```
src/
├─ dao/
├─ factory/
├─ modelo/
├─ gui/
├─ css/
```

### Bibliotecas necessárias:

* Adicione o **JavaFX SDK** no módulo do seu projeto (Module Path).
* Adicione o **mysql-connector-java.jar** na pasta `/lib` e configure no build path.

---

## ✅ Passo 3: Configuração da Conexão com o Banco (Classe `Conexao.java`)

Verifique o conteúdo da sua classe `Conexao.java` para garantir que está com URL, usuário e senha corretos:

```java
package factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    private static final String URL = "jdbc:mysql://localhost:3306/GestaoCursosAlunos";
    private static final String USUARIO = "root";
    private static final String SENHA = "sua_senha_aqui";

    public static Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, SENHA);
    }
}
```

👉 **Troque `"sua_senha_aqui"` pela senha real do seu MySQL.**

---

## ✅ Passo 4: Ajustar o VM Options da IDE

Se estiver usando **IntelliJ** ou **Eclipse**, adicione as seguintes opções no **Run Configuration (VM Options):**

```
--module-path "C:\caminho\para\javafx-sdk-21\lib" --add-modules javafx.controls,javafx.fxml
```

👉 Troque o caminho conforme a pasta do seu JavaFX.

---

## ✅ Passo 5: Executar o Sistema

* **Classe Main:** Você deve ter uma classe principal chamada algo como:

```java
public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        new AlunoGUI().show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
```

👉 Você pode trocar o GUI inicial, por exemplo:
`new CursoGUI().show();` para abrir a tela de cursos.

---

## ✅ Passo 6: Navegação e Uso Básico

### Tela de Alunos:

* Cadastrar Aluno
* Editar Aluno
* Excluir Aluno
* Desabilitar/Reabilitar
* Visualização de alunos ativos

### Tela de Cursos:

* Cadastrar Curso
* Visualizar lista de cursos ativos

*(prints das telas também)*

---

## ✅ Possíveis Erros Comuns

| Erro                               | Causa Possível                                 | Solução                                         |
| ---------------------------------- | ---------------------------------------------- | ----------------------------------------------- |
| `CSS não encontrado`               | Caminho errado no `scene.getStylesheets()`     | Verifique a pasta `/css/` e o nome do arquivo   |
| `Cannot connect to MySQL`          | Banco MySQL não iniciado ou senha errada       | Verifique URL, usuário e senha                  |
| `Module javafx.controls not found` | JavaFX não configurado corretamente no projeto | Ajuste o VM Options com o caminho do JavaFX SDK |


