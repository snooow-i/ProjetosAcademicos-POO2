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
        
        System.err.println("Nenhum mapeador encontrado para a classe: " + nomeClasse);
        return false;
    }

    public Object obter(Oid oid, Class<?> classe) {
        String nomeClasse = classe.getSimpleName();
        IMapeador mapeador = FabricaDeMapeador.obterInstancia().obterMapeador(nomeClasse);

        if (mapeador != null) {
            return mapeador.obter(oid);
        }

        System.err.println("Nenhum mapeador encontrado para a classe: " + nomeClasse);
        return null;
    }
    
    public boolean atualizar(Object objeto) {
        System.out.println("Método ATUALIZAR ainda não implementado.");
        return false;
    }

    public boolean excluir(Object objeto) {
        System.out.println("Método EXCLUIR ainda não implementado.");
        return false;
    }
}
