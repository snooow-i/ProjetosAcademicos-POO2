package poo2;

public class Matricula {
    private Aluno aluno;
    private Disciplina disciplina;

    public Matricula() {}

    public Matricula(Aluno aluno, Disciplina disciplina) {
        this.aluno = aluno;
        this.disciplina = disciplina;
    }

    public Aluno getAluno() { 
        return aluno; 
    }

    public void setAluno(Aluno aluno) { 
        this.aluno = aluno; 
    }

    public Disciplina getDisciplina() { 
        return disciplina; 
    }

    public void setDisciplina(Disciplina disciplina) { 
        this.disciplina = disciplina; 
    }
}