DROP TABLE IF EXISTS aluno;
DROP TABLE IF EXISTS disciplina;
DROP TABLE IF EXISTS aluno_disciplina;


CREATE TABLE aluno (
    id SERIAL NOT NULL,
    matricula VARCHAR(4) UNIQUE NOT NULL,
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
    aluno_id SERIAL NOT NULL,
    disciplina_id SERIAL NOT NULL,
        PRIMARY KEY (aluno_id),
            FOREIGN KEY (aluno_id) 
                REFERENCES 
                    aluno(id) ,
        PRIMARY KEY (disciplina_id),
            FOREIGN KEY (disciplina_id) 
                REFERENCES 
                    disciplina(id)
);