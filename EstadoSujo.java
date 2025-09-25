package poo2;

public class EstadoSujo implements IObjetoEstado {
    @Override
    public void salvar(ObjetoPersistente obj) {

        System.out.println("[Estado] Objeto SUJO salvo novamente. Permanece no estado SUJO.");
    }

    @Override
    public void excluir(ObjetoPersistente obj) {
        System.out.println("[Estado] Objeto SUJO excluído. Transição para o estado EXCLUÍDO.");
        obj.setEstado(new EstadoExcluido());
    }
}