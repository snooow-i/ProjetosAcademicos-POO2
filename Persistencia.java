package poo2;

public class Persistencia {
    private static Persistencia instancia = null;

    private Persistencia() {}

    public static Persistencia obterInstancia() {
        if (instancia == null) {
            instancia = new Persistencia();
        }
        return instancia;
    }
    
    public boolean inserir(Object objeto) {
        String nomeClasse = objeto.getClass().getSimpleName();
        IMapeador mapeador = FabricaDeMapeador.obterInstancia().obterMapeador(nomeClasse);
        if (mapeador != null) {
            return mapeador.inserir(objeto);
        }
        return false;
    }

    public Object obter(Oid oid, Class<?> classe) {
        String nomeClasse = classe.getSimpleName();
        IMapeador mapeador = FabricaDeMapeador.obterInstancia().obterMapeador(nomeClasse);
        if (mapeador != null) {
            return mapeador.obter(oid);
        }
        return null;
    }
    
    public boolean atualizar(Object objeto) {
        String nomeClasse = objeto.getClass().getSimpleName();
        IMapeador mapeador = FabricaDeMapeador.obterInstancia().obterMapeador(nomeClasse);
        if (mapeador != null) {
            return mapeador.atualizar(objeto);
        }
        return false;
    }

    public boolean excluir(Object objeto) {
        String nomeClasse = objeto.getClass().getSimpleName();
        IMapeador mapeador = FabricaDeMapeador.obterInstancia().obterMapeador(nomeClasse);
        if (mapeador != null) {
            return mapeador.excluir(objeto);
        }
        return false;
    }

    public void recarregar(ObjetoPersistente objeto) {
        String nomeClasse = objeto.getClass().getSimpleName();
        IMapeador mapeador = FabricaDeMapeador.obterInstancia().obterMapeador(nomeClasse);
        if (mapeador != null) {
            mapeador.recarregar(objeto);
    }
}
}