public class Disciplina {

    private String id;
    private String codigo;
    private String nome;
    private int CargaHoraria;

    public Disciplina() {
    }

    public Disciplina(String id, String codigo, String nome, int CargaHoraria) {
        this.id = id;
        this.codigo = codigo;
        this.nome = nome;
        this.CargaHoraria = CargaHoraria;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCargaHoraria() 
        return CargaHoraria;
    }

    public void setCargaHoraria(int cargaHoraria) {
        this.CargaHoraria = CargaHoraria;
    }

}