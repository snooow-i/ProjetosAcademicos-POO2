public class Aluno {

    private String id;
    private int matricula;
    private String nome;

    public Aluno() {
    }

    public Aluno(String id, int matricula, String nome) {
        this.id = id;
        this.matricula = matricula;
        this.nome = nome;
    }

    public String getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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