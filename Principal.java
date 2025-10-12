package poo2;

import java.util.Scanner;

public class Principal {

    public static void main(String[] args) {
        System.out.println("==========================================================");
        System.out.println("== TESTE DO MÉTODO RETROCEDER EM ESTADO ANTIGO SUJO ==");
        System.out.println("==========================================================");

        Persistencia persistencia = Persistencia.obterInstancia();
        Transacao transacao = Transacao.obterInstancia();
        Scanner entrada = new Scanner(System.in);
        
        Aluno alunoParaTeste = null;
        String nomeOriginal = "Isaac Newton";
        String nomeModificado = "Isaac Newton (Alterado)";

        // --- FASE 1: SETUP - Inserir um objeto para podermos testar ---
        try {
            System.out.println("\n--- FASE 1: INSERINDO OBJETO DE TESTE ---");
            alunoParaTeste = new Aluno();
            alunoParaTeste.setMatricula(20251122); // Use uma matrícula nova
            alunoParaTeste.setNome(nomeOriginal);

            transacao.adicionarInserir(alunoParaTeste);
            transacao.efetivar();

            if (alunoParaTeste.getId() == null) {
                System.err.println("   > FALHA: Não foi possível inserir o objeto de teste inicial.");
                entrada.close();
                return;
            }
            System.out.println("   > SUCESSO! Aluno '" + alunoParaTeste.getNome() + "' inserido com ID: " + alunoParaTeste.getId());
            System.out.println("   >>> Pressione Enter para continuar...");
            entrada.nextLine();
        } catch (Exception e) {
            System.err.println("   > ERRO INESPERADO NA FASE DE SETUP:");
            e.printStackTrace();
            entrada.close();
            return;
        }

        // --- FASE 2: TESTE DO RETROCEDER ---
        try {
            System.out.println("\n--- FASE 2: TESTANDO O 'DESCARTAR ALTERAÇÕES' ---");
            
            // 2.1: Recuperamos o objeto para garantir que está limpo
            Oid oidDoAluno = new Oid(alunoParaTeste.getId().toString());
            Aluno alunoRecuperado = (Aluno) persistencia.obter(oidDoAluno, Aluno.class);

            System.out.println("   1. Objeto recuperado. Nome: '" + alunoRecuperado.getNome() + "'. Estado: " + alunoRecuperado.getEstado().tipo());

            // 2.2: Modificamos o objeto, fazendo-o transitar para ANTIGO_SUJO
            System.out.println("   2. Modificando nome para: '" + nomeModificado + "'");
            alunoRecuperado.setNome(nomeModificado);
            System.out.println("      > Estado após modificação: " + alunoRecuperado.getEstado().tipo());

            // 2.3: Chamamos retroceder() para descartar a alteração
            System.out.println("   3. Chamando o método retroceder()...");
            alunoRecuperado.retroceder();

            // 2.4: Verificamos o resultado
            System.out.println("   4. Verificando o estado final do objeto:");
            System.out.println("      > Nome atual do objeto: '" + alunoRecuperado.getNome() + "'");
            System.out.println("      > Estado final do objeto: " + alunoRecuperado.getEstado().tipo());

            // 2.5: Verificação final
            if (alunoRecuperado.getNome().equals(nomeOriginal) && alunoRecuperado.getEstado().tipo() == TipoObjetoEstado.ANTIGO_LIMPO) {
                System.out.println("\n   >>> SUCESSO! As alterações foram descartadas e o estado voltou para ANTIGO_LIMPO. <<<");
            } else {
                System.err.println("\n   >>> FALHA! O objeto não foi revertido para o seu estado original. <<<");
            }

        } catch (Exception e) {
            System.err.println("   > ERRO INESPERADO NA FASE DE TESTE:");
            e.printStackTrace();
        } finally {
            entrada.close();
        }

        System.out.println("\n==========================================================");
        System.out.println("==                     FIM DO TESTE                     ==");
        System.out.println("==========================================================");
    }
}