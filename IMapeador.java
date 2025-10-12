package poo2;

public interface IMapeador {
    boolean inserir(Object obj);
    boolean atualizar(Object obj);
    boolean excluir(Object obj);
    Object obter(Oid oid);
    void recarregar(ObjetoPersistente obj);
}