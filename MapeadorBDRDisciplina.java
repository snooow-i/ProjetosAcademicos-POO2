package poo2;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MapeadorBDRDisciplina extends MapeadorDeBDRAbstrato {

    public MapeadorBDRDisciplina(String nomeTabela) {
        super(nomeTabela); 
    }

    @Override
    protected String obterNomesColunasInsert() {
        return "codigo, nome, carga_horaria";
    }

    @Override
    protected String obterInterrogacoesInsert() {
        return "?, ?, ?";
    }

    @Override
    protected void preencherParametrosInsert(PreparedStatement stm, Object obj) throws SQLException {
        Disciplina disciplina = (Disciplina) obj;
        stm.setString(1, disciplina.getCodigo());
        stm.setString(2, disciplina.getNome());
        stm.setInt(3, disciplina.getCargaHoraria());
    }

    @Override
    protected void definirId(Object obj, long id) {
        ((Disciplina) obj).setId(id);
    }
    
    @Override
    protected Object construirObjeto(ResultSet rs) throws SQLException {
        Disciplina disciplina = new Disciplina();
        disciplina.setId(rs.getLong("id"));
        disciplina.setCodigo(rs.getString("codigo"));
        disciplina.setNome(rs.getString("nome"));
        disciplina.setCargaHoraria(rs.getInt("carga_horaria"));
        return disciplina;
    }

    @Override
    protected String obterSetValuesUpdate() {
        return "codigo = ?, nome = ?, carga_horaria = ?";
    }

    @Override
    protected int preencherParametrosUpdate(PreparedStatement stm, Object obj) throws SQLException {
        Disciplina d = (Disciplina) obj;
        stm.setString(1, d.getCodigo());
        stm.setString(2, d.getNome());
        stm.setInt(3, d.getCargaHoraria());
        return 4;
    }

    @Override
    protected void copiarDados(ObjetoPersistente destino, ObjetoPersistente origem) {
        Disciplina disciplinaDestino = (Disciplina) destino;
        Disciplina disciplinaOrigem = (Disciplina) origem;
    
        disciplinaDestino.setCodigo(disciplinaOrigem.getCodigo());
        disciplinaDestino.setNome(disciplinaOrigem.getNome());
        disciplinaDestino.setCargaHoraria(disciplinaOrigem.getCargaHoraria());
    }
}