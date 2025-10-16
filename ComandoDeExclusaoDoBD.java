package poo2;

public class ComandoDeExclusaoDoBD extends ComandoDeBDAdapter {

    public ComandoDeExclusaoDoBD(ObjetoPersistente ob) {
        super(ob);
    }
    
    @Override
    public boolean executar() {
        if (objeto.getEstado().tipo() == TipoObjetoEstado.ANTIGO_LIMPO || objeto.getEstado().tipo() == TipoObjetoEstado.ANTIGO_SUJO) {
            objeto.excluir();
            return true;
        } else if (objeto.getEstado().tipo() == TipoObjetoEstado.ANTIGO_EXCLUIDO) {
            return objeto.efetivar();
        } else {
            return false;
        }
    }
}