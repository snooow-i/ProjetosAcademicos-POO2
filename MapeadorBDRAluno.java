package poo2;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MapeadorBDRAluno extends MapeadorDeBDRAbstrato {

    public MapeadorBDRAluno(String nomeTabela) {
        super(nomeTabela);
    }

    @Override
    protected String obterNomesColunasInsert() {
        return "matricula, nome";
    }

    @Override
    protected String obterInterrogacoesInsert() {
        return "?, ?";
    }

    @Override
    protected void preencherParametrosInsert(PreparedStatement stm, Object obj) throws SQLException {
        Aluno aluno = (Aluno) obj;
        stm.setInt(1, aluno.getMatricula());
        stm.setString(2, aluno.getNome());
    }
    
    @Override
    protected void definirId(Object obj, long id) {
        ((Aluno) obj).setId(id);
    }

    @Override
    protected Object construirObjeto(ResultSet rs) throws SQLException {
        Aluno aluno = new Aluno();
        aluno.setId(rs.getLong("id"));
        aluno.setMatricula(rs.getInt("matricula"));
        aluno.setNome(rs.getString("nome"));
        return aluno;
    }

    @Override
    protected String obterSetValuesUpdate() {
        return "matricula = ?, nome = ?";
    }

    @Override
    protected int preencherParametrosUpdate(PreparedStatement stm, Object obj) throws SQLException {
        Aluno aluno = (Aluno) obj;
        stm.setInt(1, aluno.getMatricula());
        stm.setString(2, aluno.getNome());
        return 3;
    }

    @Override
    protected void copiarDados(ObjetoPersistente destino, ObjetoPersistente origem) {
        Aluno alunoDestino = (Aluno) destino;
        Aluno alunoOrigem = (Aluno) origem;
        
        alunoDestino.setNome(alunoOrigem.getNome());
        alunoDestino.setMatricula(alunoOrigem.getMatricula());
}
}