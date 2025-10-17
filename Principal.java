package poo2;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Principal {

    private static final Persistencia persistencia = Persistencia.obterInstancia();
    private static final Transacao transacao = Transacao.obterInstancia();
    private static final Scanner entrada = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("==========================================================");
        System.out.println("=========        INICIANDO TESTES COMPLETOS       ========");
        System.out.println("==========================================================");

        testeInsercaoELeitura();
        pressioneEnterParaContinuar();
        testeUpdateEmDuasFases();
        pressioneEnterParaContinuar();
        testeDeleteEmDuasFases();
        pressioneEnterParaContinuar();
        testeRetroceder();

        System.out.println("\n==========================================================");
        System.out.println("===============      TESTES FINALIZADOS     ================");
        System.out.println("============================================================");
        entrada.close();
    }

    private static void testeInsercaoELeitura() {
        System.out.println("\n---  INSERÇÃO E LEITURA ---");
        limparTabelas();

        Aluno novoAluno = new Aluno();
        novoAluno.setMatricula(202501);
        novoAluno.setNome("Guilherme");
        transacao.adicionarInserir(novoAluno);

        System.out.println("   1. Tentando inserir 'Guilherme'...");
        transacao.efetivar();

        if (novoAluno.getId() != null && novoAluno.getEstado().tipo() == TipoObjetoEstado.ANTIGO_LIMPO) {
            System.out.println("   > SUCESSO! Aluno inserido com ID: " + novoAluno.getId() + " e estado: " + novoAluno.getEstado().tipo());
        } else {
            System.err.println("   > FALHA na inserção!");
            return;
        }

        System.out.println("\n   2. Tentando ler o Aluno (ID: " + novoAluno.getId() + ") do banco...");
        Aluno alunoLido = (Aluno) persistencia.obter(new Oid(novoAluno.getId().toString()), Aluno.class);

        if (alunoLido != null && alunoLido.getNome().equals("Guilherme")) {
            System.out.println("   > SUCESSO! Aluno lido corretamente. Nome: '" + alunoLido.getNome() + "'.");
        } else {
            System.err.println("   > FALHA na leitura!");
        }
    }

    private static void testeUpdateEmDuasFases() {
        System.out.println("\n--- ATUALIZAÇÃO EM DUAS FASES ---");
        limparTabelas();

        Aluno aluno = new Aluno();
        aluno.setMatricula(202502);
        aluno.setNome("Nikola Tesla");
        transacao.adicionarInserir(aluno);
        transacao.efetivar();
        System.out.println("   [SETUP] Aluno 'Nikola Tesla' inserido no banco.");

        System.out.println("\n   1. FASE 1 - Preparando para atualização...");
        aluno.setNome("Nikola Tesla (Alterado)");
        transacao.adicionarAtualizar(aluno);
        transacao.efetivar();
        System.out.println("     > Primeira efetivação concluída. Estado em memória: " + aluno.getEstado().tipo());
        System.out.println("     > VERIFIQUE O BANCO: O nome do aluno NÃO deve ter sido alterado ainda.");
        pressioneEnterParaContinuar();

        System.out.println("   2. FASE 2 - Confirmando a atualização...");
        transacao.adicionarAtualizar(aluno);
        transacao.efetivar();
        System.out.println("     > Segunda efetivação concluída. Estado em memória: " + aluno.getEstado().tipo());
        
        Aluno alunoVerificacao = (Aluno) persistencia.obter(new Oid(aluno.getId().toString()), Aluno.class);
        if (alunoVerificacao.getNome().equals("Nikola Tesla (Alterado)")) {
             System.out.println("   > SUCESSO! O nome foi atualizado no banco.");
             System.out.println("   > VERIFIQUE O BANCO: O nome do aluno AGORA deve estar atualizado.");
        } else {
            System.err.println("   > FALHA! O nome não foi atualizado no banco.");
        }
    }

    private static void testeDeleteEmDuasFases() {
        System.out.println("\n--- EXCLUSÃO EM DUAS FASES ---");
        limparTabelas();

        Disciplina disciplina = new Disciplina();
        disciplina.setCodigo("BIO01");
        disciplina.setNome("Genética");
        transacao.adicionarInserir(disciplina);
        transacao.efetivar();
        System.out.println("   [SETUP] Disciplina 'Genética' inserida no banco.");

        System.out.println("\n   1. FASE 1 - Preparando para exclusão...");
        transacao.adicionarExcluir(disciplina);
        transacao.efetivar();
        System.out.println("     > Primeira efetivação concluída. Estado em memória: " + disciplina.getEstado().tipo());
        System.out.println("     > VERIFIQUE O BANCO: A disciplina AINDA deve existir.");
        pressioneEnterParaContinuar();

        System.out.println("   2. FASE 2 - Confirmando a exclusão...");
        transacao.adicionarExcluir(disciplina);
        transacao.efetivar();
        System.out.println("     > Segunda efetivação concluída. Estado em memória: " + disciplina.getEstado().tipo());

        Disciplina disciplinaVerificacao = (Disciplina) persistencia.obter(new Oid(disciplina.getId().toString()), Disciplina.class);
        if (disciplinaVerificacao == null) {
             System.out.println("   > SUCESSO! A disciplina foi removida do banco.");
             System.out.println("   > VERIFIQUE O BANCO: A disciplina AGORA não deve mais existir.");
        } else {
            System.err.println("   > FALHA! A disciplina não foi removida do banco.");
        }
    }

    private static void testeRetroceder() {
        System.out.println("\n--- RETROCEDER ---");
        limparTabelas();

        Aluno aluno = new Aluno();
        aluno.setMatricula(202503);
        aluno.setNome("Galileo Galilei");
        transacao.adicionarInserir(aluno);
        transacao.efetivar();
        System.out.println("   [SETUP] Aluno 'Galileo Galilei' inserido no banco.");
        
        aluno.setNome("Galileo Galilei (Alterado)");
        aluno.salvar(); 
        System.out.println("   1. Aluno modificado em memória. Nome: '" + aluno.getNome() + "', Estado: " + aluno.getEstado().tipo());
        
        System.out.println("   2. Chamando .retroceder()...");
        aluno.retroceder();

        if (aluno.getNome().equals("Galileo Galilei") && aluno.getEstado().tipo() == TipoObjetoEstado.ANTIGO_LIMPO) {
            System.out.println("   > SUCESSO! O estado e os dados em memória foram revertidos.");
            System.out.println("     - Nome final: '" + aluno.getNome() + "', Estado final: " + aluno.getEstado().tipo());
        } else {
            System.err.println("   > FALHA! O método retroceder não funcionou como esperado.");
        }
    }


    private static void limparTabelas() {
        System.out.println("\n--- [SETUP] Limpando tabelas do banco de dados...");
        try (Connection conexao = FabricaConexaoBD.obterInstancia().getConexao();
             Statement stm = conexao.createStatement()) {

            stm.execute("DELETE FROM aluno");
            stm.execute("DELETE FROM disciplina");
  
            conexao.commit();
            System.out.println("   > Tabelas limpas com sucesso.");
        } catch (SQLException e) {
            System.err.println("   > FALHA AO LIMPAR TABELAS: " + e.getMessage());
        }
    }

    private static void pressioneEnterParaContinuar() {
        System.out.println("\n   ... Pressione Enter para continuar para o próximo teste ...");
        entrada.nextLine();
    }
}