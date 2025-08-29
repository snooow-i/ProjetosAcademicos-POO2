package poo2;

public class Persistencia {

    private static Persistencia instancia = null;

    private Persistencia() {
    }

    public static Persistencia obterInstancia() {
        if (instancia == null) {
            instancia = new Persistencia();
        }
        return instancia;
    }

    public Object obter(Oid oid, Class<?> classe) {
        return null;
    }

    public void inserir(Object objeto) {
        
    }

    public void atualizar(Object objeto) {
    }

    public void excluir(Object objeto) {
    }

}
