package poo2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class FabricaConexaoBD {
    private static final String URL = "jdbc:postgresql://localhost:5432/faculdade"; 
    private static final String USER = "postgres"; 
    private static final String PASSWORD = "postgres";

    private static FabricaConexaoBD instancia = null;

    private FabricaConexaoBD() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Driver JDBC do PostgreSQL não encontrado.", e);
        }
    }

    public static FabricaConexaoBD obterInstancia() {
        if (instancia == null) {
            instancia = new FabricaConexaoBD();
        }
        return instancia;
    }

    public Connection getConexao() throws SQLException {
        Connection novaConexao = DriverManager.getConnection(URL, USER, PASSWORD);
        novaConexao.setAutoCommit(false);
        return novaConexao;
    }

}
