package poo2;

public class EstadoExcluido implements IObjetoEstado {
    @Override
    public void salvar(ObjetoPersistente obj) {
        System.out.println("[Estado] ERRO: Não é possível salvar um objeto no estado EXCLUÍDO.");
    }

    @Override
    public void excluir(ObjetoPersistente obj) {
        System.out.println("[Estado] Objeto já está no estado EXCLUÍDO.");
    }
}