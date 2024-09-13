CREATE DATABASE imt_ti;

USE imt_ti;

CREATE TABLE alunos (
    aluno_id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    senha VARCHAR(100) NOT NULL
);

CREATE TABLE materias (
    materia_id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL
);

CREATE TABLE notas_faltas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    aluno_id INT,
    materia_id INT,
    nota DECIMAL(5,2),
    faltas INT,
    FOREIGN KEY (aluno_id) REFERENCES alunos(aluno_id),
    FOREIGN KEY (materia_id) REFERENCES materias(materia_id)
);

INSERT INTO alunos (nome, senha) VALUES ('João Silva', 'senha123');
INSERT INTO materias (nome) VALUES ('Matemática'), ('Programação'), ('Redes');
INSERT INTO notas_faltas (aluno_id, materia_id, nota, faltas) VALUES (1, 1, 8.5, 2), (1, 2, 9.0, 1), (1, 3, 7.5, 3);
