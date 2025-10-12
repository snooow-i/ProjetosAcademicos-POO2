package poo2;

import java.util.LinkedList;
import java.util.Iterator;

public class Transacao {
    private static Transacao instancia = null;
    private LinkedList<IComando> comandos;

    private Transacao() {
        comandos = new LinkedList<IComando>();
    }

    public static Transacao obterInstancia() {
        if (instancia == null) {
            instancia = new Transacao();
        }
        return instancia;
    }

    public void adicionarInserir(ObjetoPersistente ob) {
        comandos.add(new ComandoDeInsercaoNoBD(ob));
    }
    
    public void adicionarAtualizar(ObjetoPersistente ob) {
        comandos.add(new ComandoDeAtualizacaoDoBD(ob));
    }

    public void adicionarExcluir(ObjetoPersistente ob) {
        comandos.add(new ComandoDeExclusaoDoBD(ob));
    }

    public boolean efetivar() {
        boolean resultado = true;
        Iterator<IComando> it = comandos.iterator();
        while (it.hasNext()) {
            IComando comando = (IComando) it.next();
            resultado = comando.executar();
        }
        comandos.clear();
        return resultado;
}
}
