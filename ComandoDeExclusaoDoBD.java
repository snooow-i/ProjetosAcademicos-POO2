package poo2;

public class ComandoDeExclusaoDoBD extends ComandoDeBDAdapter {
    public ComandoDeExclusaoDoBD(ObjetoPersistente ob) {
        super(ob);
    }
    
    @Override
    public boolean executar() {
        objeto.excluir();
        return objeto.efetivar();
    }
}