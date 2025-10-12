package poo2;

public class EstadoAntigoSujo extends ObjetoEstadoAdapter {
    private static EstadoAntigoSujo instancia = null;

    private EstadoAntigoSujo() {
        tipo = TipoObjetoEstado.ANTIGO_SUJO;
    }

    public static EstadoAntigoSujo obterInstancia() {
        if (instancia == null) {
            instancia = new EstadoAntigoSujo();
        }
        return instancia;
    }

@Override
    public boolean efetivar(ObjetoPersistente ob) {
        boolean resultado = Persistencia.obterInstancia().atualizar(ob); 
        if (resultado) {
            ob.setEstado(EstadoAntigoLimpo.obterInstancia());
        }
        return resultado;
    }

    @Override
    public boolean excluir(ObjetoPersistente ob) {
        ob.setEstado(EstadoAntigoExcluido.obterInstancia());
        return true;
    }

    @Override
    public boolean retroceder(ObjetoPersistente ob) {
        Persistencia.obterInstancia().recarregar(ob);
    
        ob.setEstado(EstadoAntigoLimpo.obterInstancia());
    
        System.out.println("[Estado] Alterações descartadas. Objeto SUJO voltou para o estado LIMPO.");
        return true;
    }
}