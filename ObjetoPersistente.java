package poo2;

public abstract class ObjetoPersistente {
    private Long id;
    private IObjetoEstado estado;

    public ObjetoPersistente() {
        this.estado = EstadoNovo.obterInstancia();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    protected void setEstado(IObjetoEstado estado) {
        this.estado = estado;
    }

    public IObjetoEstado getEstado() {
        return this.estado;
    }

    public boolean efetivar() {
        return this.estado.efetivar(this);
    }

    public boolean excluir() {
        return this.estado.excluir(this);
    }

    public boolean retroceder() {
        return this.estado.retroceder(this);
    }

    public boolean salvar() {
        return this.estado.salvar(this);
    }

}