// Pacote: poo2
package poo2;

// Classe Contexto do Padrão State
public abstract class ObjetoPersistente {
    
    private Long id;
    private IObjetoEstado estado; // Referência para o estado atual

    public ObjetoPersistente() {
        // Todo objeto começa no estado NOVO por padrão
        this.estado = new EstadoNovo();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    protected void setEstado(IObjetoEstado estado) {
        this.estado = estado;
    }
    
    public IObjetoEstado getEstado() {
        return this.estado;
    }

    // Métodos que delegam o comportamento para o objeto de estado atual
    public void salvar() {
        this.estado.salvar(this);
    }
    
    public void excluir() {
        this.estado.excluir(this);
    }
}