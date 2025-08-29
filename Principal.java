package poo2;

public class Principal {

    public static void main(String[] args) {
        System.out.println("=============================================");
        System.out.println("== INÍCIO DOS TESTES DO FRAMEWORK (STAGE 2) ==");
        System.out.println("=============================================");

        // Obter a instância da nossa Fachada de Persistência
        Persistencia persistencia = Persistencia.obterInstancia();
        
        // --- TESTE 1: INSERÇÃO DE UM NOVO ALUNO ---
        System.out.println("\n--- TESTE 1: INSERÇÃO ---");
        
        // 1.1: Criar um objeto Aluno em memória
        Aluno novoAluno = new Aluno();
        novoAluno.setMatricula(20450007); // Use uma matrícula que ainda não exista
        novoAluno.setNome("Vasco da Gama");
        System.out.println("   > Objeto a ser inserido: " + novoAluno.getNome() + " (ID=" + novoAluno.getId() + ")");
        
        // 1.2: Chamar a fachada para inserir
        boolean inseriu = persistencia.inserir(novoAluno);
        
        // 1.3: Verificar o resultado
        if (inseriu && novoAluno.getId() != null) {
            System.out.println("   > SUCESSO! Aluno inserido com o ID: " + novoAluno.getId());
        } else {
            System.err.println("   > FALHA! Não foi possível inserir o aluno.");
            return; // Interrompe o teste se a inserção falhar
        }

        // --- TESTE 2: RECUPERAÇÃO DE UM ALUNO EXISTENTE ---
        System.out.println("\n--- TESTE 2: RECUPERAÇÃO (SUCESSO ESPERADO) ---");
        
        // 2.1: Usar o ID do aluno que acabamos de inserir
        Oid oidParaBuscar = new Oid(novoAluno.getId().toString());
        System.out.println("   > Buscando o aluno com OID = " + oidParaBuscar.getString() + "...");
        
        // 2.2: Chamar a fachada para obter
        Aluno alunoRecuperado = (Aluno) persistencia.obter(oidParaBuscar, Aluno.class);
        
        // 2.3: Verificar o resultado
        if (alunoRecuperado != null) {
            System.out.println("   > SUCESSO! Aluno recuperado: " + alunoRecuperado.getNome());
            System.out.println("     ID: " + alunoRecuperado.getId() + " | Matrícula: " + alunoRecuperado.getMatricula());
        } else {
            System.err.println("   > FALHA! O aluno que foi inserido não foi encontrado.");
        }

        // --- TESTE 3: RECUPERAÇÃO DE UM ALUNO INEXISTENTE ---
        System.out.println("\n--- TESTE 3: RECUPERAÇÃO (FALHA ESPERADA) ---");
        
        // 3.1: Criar um OID para um aluno que não existe
        Oid oidInexistente = new Oid("9999");
        System.out.println("   > Buscando o aluno com OID = " + oidInexistente.getString() + "...");
        
        // 3.2: Chamar a fachada para obter
        Aluno alunoInexistente = (Aluno) persistencia.obter(oidInexistente, Aluno.class);
        
        // 3.3: Verificar o resultado (o esperado é 'null')
        if (alunoInexistente == null) {
            System.out.println("   > SUCESSO! O framework retornou 'null' corretamente para um objeto inexistente.");
        } else {
            System.err.println("   > FALHA! O framework retornou um objeto quando deveria ter retornado 'null'.");
        }

        System.out.println("\n=============================================");
        System.out.println("== FIM DOS TESTES                           ==");
        System.out.println("=============================================");
    }
}
