-- Script para criação do banco de dados no MySQL
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

-- Inserção de cursos de exemplo
INSERT INTO curso (nome, cargaHoraria, limiteAlunos) VALUES 
('Análise e Desenvolvimento de Sistemas', 2400, 30),
('Engenharia de Software', 2000, 25),
('Ciências de Dados', 2000, 25),
('Redes de Computadores', 2400, 20),
('Ciência da Computação', 3200, 20);

-- Inserção de alunos exemplo (corrigindo CPF duplicado)
INSERT INTO aluno (cpf, nome, telefone, email, dataNascimento, curso) VALUES 
('12345678901', 'Raphaela Barbosa Monteiro', '11999999999', 'raphaela.monteiro@example.com', '2006-04-29', 1),
('54345678901', 'Linus Torvalds', '11977777777', 'linux.torvalds@example.com', '1969-12-28', 2),
('12345678902', 'Raul Germano Rosendo de Oliveira Duarte', '11988888888', 'raul.duarte@example.com', '2006-04-24', 1),
('11122233399', 'Hedy Lamarr', '11955555555', 'hedy.lamarr@email.com', '2002-01-19', 4),
('55045078901', 'Grace Hopper', '11944444444', 'cobol.hopper@example.com', '2000-01-28', 5),
('00045078901', 'Ada Lovelace', '11933333333', 'adalove.lace@example.com', '2000-01-28', 3),	
('01045078901', 'Alan Turing', '11933333333', 'alan.turing@example.com', '2000-06-23', 3);

