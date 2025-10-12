package poo2;

public class EstadoExcluido extends ObjetoEstadoAdapter {
    private static EstadoExcluido instancia = null;

    private EstadoExcluido() {
        tipo = TipoObjetoEstado.EXCLUIDO;
    }

    public static EstadoExcluido obterInstancia() {
        if (instancia == null) {
            instancia = new EstadoExcluido();
        }
        return instancia;
    }
    
}