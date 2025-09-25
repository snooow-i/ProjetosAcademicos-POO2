package poo2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class MapeadorDeBDRAbstrato extends MapeadorDePersistenciaAbstrato {

    protected String nomeTabela;

    public MapeadorDeBDRAbstrato(String nomeTabela) {
        this.nomeTabela = nomeTabela;
    }

    @Override
    protected boolean executarInsert(Object obj) throws SQLException {
        String sql = "INSERT INTO " + this.nomeTabela + " (" + obterNomesColunasInsert() + ") VALUES (" + obterInterrogacoesInsert() + ")";
        
        try (Connection conexao = FabricaConexaoBD.obterInstancia().getConexao();
             PreparedStatement stm = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            preencherParametrosInsert(stm, obj);
            
            int linhasAfetadas = stm.executeUpdate();

            if (linhasAfetadas > 0) {
                try (ResultSet chavesGeradas = stm.getGeneratedKeys()) {
                    if (chavesGeradas.next()) {
                        definirId(obj, chavesGeradas.getLong(1));
                    }
                }
                conexao.commit();
                return true;
            } else {
                conexao.rollback();
                return false;
            }
        }
    }

    @Override
    protected Object buscarPeloOid(Oid oid) throws SQLException {
        String sql = "SELECT * FROM " + this.nomeTabela + " WHERE id = ?";
        
        try (Connection conexao = FabricaConexaoBD.obterInstancia().getConexao();
             PreparedStatement stm = conexao.prepareStatement(sql)) {
            
            long id = Long.parseLong(oid.getString());
            stm.setLong(1, id);

            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    return construirObjeto(rs);
                }
            }
        }
        return null;
    }

    protected abstract String obterNomesColunasInsert();
    protected abstract String obterInterrogacoesInsert();
    protected abstract void preencherParametrosInsert(PreparedStatement stm, Object obj) throws SQLException;
    protected abstract void definirId(Object obj, long id);
    protected abstract Object construirObjeto(ResultSet rs) throws SQLException;
}