package poo2;

public class ComandoDeInsercaoNoBD extends ComandoDeBDAdapter {
    public ComandoDeInsercaoNoBD(ObjetoPersistente ob) {
        super(ob);
    }
    
    @Override
    public boolean executar() {
        return objeto.efetivar();
    }
}