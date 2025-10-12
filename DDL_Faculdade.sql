




DROP TABLE IF EXISTS aluno_disciplina;
DROP TABLE IF EXISTS disciplina;
DROP TABLE IF EXISTS aluno;

CREATE TABLE aluno (
    id SERIAL NOT NULL,
    matricula INT UNIQUE NOT NULL,
    nome VARCHAR(255) NOT NULL,
        PRIMARY KEY (id)
);

CREATE TABLE disciplina (
    id SERIAL NOT NULL,
    codigo VARCHAR(10) UNIQUE NOT NULL,
    nome VARCHAR(255) NOT NULL,
    carga_horaria INT NOT NULL,
        PRIMARY KEY(id)
);

CREATE TABLE aluno_disciplina (
    aluno_id INT NOT NULL,
    disciplina_id INT NOT NULL,
        PRIMARY KEY (aluno_id, disciplina_id),
            FOREIGN KEY (aluno_id) 
                REFERENCES 
                    aluno(id) ,
            FOREIGN KEY (disciplina_id) 
                REFERENCES 
                    disciplina(id)
);

