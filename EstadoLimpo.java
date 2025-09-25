package poo2;

public class EstadoLimpo implements IObjetoEstado {
    @Override
    public void salvar(ObjetoPersistente obj) {

        System.out.println("[Estado] Objeto LIMPO salvo. Transição para o estado SUJO.");
        obj.setEstado(new EstadoSujo());
    }

    @Override
    public void excluir(ObjetoPersistente obj) {

        System.out.println("[Estado] Objeto LIMPO excluído. Transição para o estado EXCLUÍDO.");
        obj.setEstado(new EstadoExcluido());
    }
}