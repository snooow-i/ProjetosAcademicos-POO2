package poo2;

public interface IObjetoEstado {
    boolean efetivar(ObjetoPersistente ob);
    boolean excluir(ObjetoPersistente ob);
    boolean retroceder(ObjetoPersistente ob);
    boolean salvar(ObjetoPersistente ob);
    TipoObjetoEstado tipo();
}