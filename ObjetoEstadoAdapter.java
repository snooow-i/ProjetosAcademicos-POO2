package poo2;

public abstract class ObjetoEstadoAdapter implements IObjetoEstado {
    protected TipoObjetoEstado tipo;

    @Override
    public boolean efetivar(ObjetoPersistente ob) { return true; }
    @Override
    public boolean excluir(ObjetoPersistente ob) { return true; }
    @Override
    public boolean retroceder(ObjetoPersistente ob) { return true; }
    @Override
    public boolean salvar(ObjetoPersistente ob) { return true; }

    @Override
    public TipoObjetoEstado tipo() {
        return tipo;
    }
}