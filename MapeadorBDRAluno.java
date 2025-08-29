package poo2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MapeadorBDRAluno implements IMapeador {

    private Connection conexao;

    public MapeadorBDRAluno() {
        this.conexao = FabricaConexaoBD.obterInstancia().getConexao();
    }

    @Override
    public boolean inserir(Object obj) {
        Aluno aluno = (Aluno) obj;
        String sql = "INSERT INTO aluno (matricula, nome) VALUES (?, ?)";
        
        try {
            PreparedStatement stm = conexao.prepareStatement(sql);
            stm.setInt(1, aluno.getMatricula());
            stm.setString(2, aluno.getNome());

            stm.executeUpdate();
            stm.close();

            buscarId(aluno);

            return true;

        } catch (SQLException e) {
            System.err.println("Erro ao inserir aluno: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    private void buscarId(Aluno aluno) throws SQLException {
        String sql = "SELECT id FROM aluno WHERE matricula = ?";
        
        PreparedStatement stm = conexao.prepareStatement(sql);
        stm.setInt(1, aluno.getMatricula());
        
        ResultSet rs = stm.executeQuery();
        if (rs.next()) {
            aluno.setId(rs.getLong("id")); 
        }
        rs.close();
        stm.close();
    }

    @Override
    public Object obter(Oid oid) {
        String sql = "SELECT * FROM aluno WHERE id = ?";
        Aluno aluno = null;
        
        try {
            PreparedStatement stm = conexao.prepareStatement(sql);
            long id = Long.parseLong(oid.getString());
            stm.setLong(1, id);

            ResultSet rs = stm.executeQuery();

            if (rs.next()) {
                aluno = new Aluno();
                aluno.setId(rs.getLong("id"));
                aluno.setMatricula(rs.getInt("matricula"));
                aluno.setNome(rs.getString("nome"));
            }
            
            rs.close();
            stm.close();

        } catch (SQLException | NumberFormatException e) {
            System.err.println("Erro ao obter aluno: " + e.getMessage());
            e.printStackTrace();
        }
        
        return aluno;
    }

    
    public boolean atualizar(Object obj) {
        return false;
    }

    
    public boolean excluir(Object obj) {
        return false;
    }
}
