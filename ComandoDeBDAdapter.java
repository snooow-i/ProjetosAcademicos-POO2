package poo2;

public abstract class ComandoDeBDAdapter implements IComando {
    protected ObjetoPersistente objeto;
    
    public ComandoDeBDAdapter(ObjetoPersistente ob) {
        this.objeto = ob;
    }

    @Override
    public boolean executar() {
        return false;
    }

    @Override
    public boolean desfazer() {
        return objeto.retroceder();
    }
}