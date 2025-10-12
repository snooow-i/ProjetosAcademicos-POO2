package poo2;

public class ComandoDeAtualizacaoDoBD extends ComandoDeBDAdapter {
    public ComandoDeAtualizacaoDoBD(ObjetoPersistente ob) {
        super(ob);
    }
    
    @Override
    public boolean executar() {
        if (objeto.getEstado().tipo() == TipoObjetoEstado.ANTIGO_LIMPO) {
            objeto.salvar();
        }
        return objeto.efetivar();
    }
}