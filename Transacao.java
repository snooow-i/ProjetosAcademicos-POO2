package poo2;

import java.util.ArrayList;
import java.util.List;

import java.util.LinkedList;

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
    List<IComando> comandosExecutados = new ArrayList<>();

    for (IComando comando : this.comandos) {
        if (comando.executar()) {
            comandosExecutados.add(comando);
        } else {
            System.err.println("Falha na execução de um comando. Iniciando rollback...");
            for (int i = comandosExecutados.size() - 1; i >= 0; i--) {
                comandosExecutados.get(i).desfazer();
            }
            this.comandos.clear();
            return false;
        }
    }

    this.comandos.clear();
    System.out.println("Transação efetivada com sucesso!");
    return true;
}
}