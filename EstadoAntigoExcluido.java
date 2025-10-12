package poo2;

public class EstadoAntigoExcluido extends ObjetoEstadoAdapter {
    private static EstadoAntigoExcluido instancia = null;

    private EstadoAntigoExcluido() {
        tipo = TipoObjetoEstado.ANTIGO_EXCLUIDO;
    }

    public static EstadoAntigoExcluido obterInstancia() {
        if (instancia == null) {
            instancia = new EstadoAntigoExcluido();
        }
        return instancia;
    }

    @Override
    public boolean efetivar(ObjetoPersistente ob) {
        boolean resultado = Persistencia.obterInstancia().excluir(ob); // Chama o DELETE
        if (resultado) {
            ob.setEstado(EstadoExcluido.obterInstancia());
        }
        return resultado;
    }

    @Override
    public boolean retroceder(ObjetoPersistente ob) {
        ob.setEstado(EstadoAntigoLimpo.obterInstancia());
        return true;
    }
}