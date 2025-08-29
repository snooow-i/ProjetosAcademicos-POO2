package poo2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class FabricaConexaoBD {

    private static final String URL = "jdbc:postgresql://localhost:5432/faculdade"; 
    private static final String USER = "postgres"; 
    private static final String PASSWORD = "postgres"; 

    private static FabricaConexaoBD instancia = null;
    private Connection conexao = null;

    private FabricaConexaoBD() {
        try {
            Class.forName("org.postgresql.Driver");
            
            this.conexao = DriverManager.getConnection(URL, USER, PASSWORD);
            
        } catch (ClassNotFoundException e) {
            System.err.println("Driver JDBC do PostgreSQL não encontrado.");
            throw new RuntimeException(e);
        } catch (SQLException e) {
            System.err.println("Falha ao conectar ao banco de dados: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public static FabricaConexaoBD obterInstancia() {
        if (instancia == null) {
            instancia = new FabricaConexaoBD();
        }
        return instancia;
    }

    public Connection getConexao() {
        return this.conexao;
    }

}

