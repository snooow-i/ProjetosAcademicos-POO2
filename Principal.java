package poo2;

public class Principal {
    public static void main(String[] args) {
        Persistencia persistencia = Persistencia.obterInstancia();
        
        System.out.println("=============================================");
        System.out.println("== INÍCIO DOS TESTES DO FRAMEWORK (STAGE 4) ==");
        System.out.println("=============================================");

        System.out.println("\n--- TESTE DE INSERÇÃO DE ALUNO ---");
        Aluno novoAluno = new Aluno();
        novoAluno.setMatricula(20250009);
        novoAluno.setNome("Vasco da Gama");
        
        System.out.println("   > Objeto a ser inserido: " + novoAluno.getNome() + " (ID=" + novoAluno.getId() + ")");
        persistencia.inserir(novoAluno);
        System.out.println("   > SUCESSO! Aluno inserido com o ID: " + novoAluno.getId());

        System.out.println("\n--- TESTE DE INSERÇÃO DE DISCIPLINA ---");
        Disciplina novaDisciplina = new Disciplina();
        novaDisciplina.setCodigo("COMP0408");
        novaDisciplina.setNome("Estrutura de Dados II");
        novaDisciplina.setCargaHoraria(60);

        System.out.println("   > Objeto a ser inserido: " + novaDisciplina.getNome() + " (ID=" + novaDisciplina.getId() + ")");
        persistencia.inserir(novaDisciplina);
        System.out.println("   > SUCESSO! Disciplina inserida com o ID: " + novaDisciplina.getId());

        System.out.println("\n=============================================");
        System.out.println("== FIM DOS TESTES                           ==");
        System.out.println("=============================================");
    }
}
