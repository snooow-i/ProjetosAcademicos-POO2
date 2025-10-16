package poo2;

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
        boolean resultadoComando = true;
        int posicaoErro;
        int i = 0;
        
        while (resultadoComando && i < comandos.size()) {
            resultadoComando = comandos.get(i).executar();
            if (!resultadoComando) {
                posicaoErro = i;
                System.err.println("[Transacao] FALHA ao executar comando na posição " + posicaoErro + ". Iniciando rollback em memória...");
                for (int j = 0; j <= posicaoErro; j++) {
                    comandos.get(j).desfazer();
                }
            }
            i++;
        }
        
        comandos.clear();
        return resultadoComando;
    }


}