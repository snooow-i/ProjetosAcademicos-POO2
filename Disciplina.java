package poo2;

public class Disciplina extends ObjetoPersistente {

    private String codigo;
    private String nome;
    private int cargaHoraria;

    public Disciplina() {
        super();
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
        this.salvar();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
        this.salvar();
    }

    public int getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(int cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
        this.salvar();
    }
}
