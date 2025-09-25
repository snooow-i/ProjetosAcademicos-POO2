package poo2;

public class EstadoNovo implements IObjetoEstado {
    @Override
    public void salvar(ObjetoPersistente obj) {

        System.out.println("[Estado] Objeto NOVO salvo. Transição para o estado SUJO.");
        obj.setEstado(new EstadoSujo());
    }

    @Override
    public void excluir(ObjetoPersistente obj) {

        System.out.println("[Estado] Objeto NOVO excluído. Transição para o estado EXCLUÍDO.");
        obj.setEstado(new EstadoExcluido());
    }
}