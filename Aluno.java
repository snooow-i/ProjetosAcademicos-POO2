package poo2;

public class Aluno extends ObjetoPersistente {
    private int matricula;
    private String nome;

    public Aluno() {
        super();
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}