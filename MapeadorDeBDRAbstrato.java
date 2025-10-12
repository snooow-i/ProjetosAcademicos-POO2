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
                    ObjetoPersistente obj = (ObjetoPersistente) construirObjeto(rs);
                    obj.setEstado(EstadoAntigoLimpo.obterInstancia());
                    return obj;
                }
            }
        }
        return null;
    }

    @Override
    protected boolean executarUpdate(Object obj) throws SQLException {
        ObjetoPersistente pobj = (ObjetoPersistente) obj;
        String sql = "UPDATE " + this.nomeTabela + " SET " + obterSetValuesUpdate() + " WHERE id = ?";
        try (Connection conexao = FabricaConexaoBD.obterInstancia().getConexao();
             PreparedStatement stm = conexao.prepareStatement(sql)) {
            int ultimoIndice = preencherParametrosUpdate(stm, pobj);
            stm.setLong(ultimoIndice, pobj.getId());
            int linhasAfetadas = stm.executeUpdate();
            if (linhasAfetadas > 0) {
                conexao.commit();
                return true;
            } else {
                conexao.rollback();
                return false;
            }
        }
    }

    @Override
    protected boolean executarDelete(Object obj) throws SQLException {
        ObjetoPersistente pobj = (ObjetoPersistente) obj;
        String sql = "DELETE FROM " + this.nomeTabela + " WHERE id = ?";
        try (Connection conexao = FabricaConexaoBD.obterInstancia().getConexao();
             PreparedStatement stm = conexao.prepareStatement(sql)) {
            stm.setLong(1, pobj.getId());
            int linhasAfetadas = stm.executeUpdate();
            if (linhasAfetadas > 0) {
                conexao.commit();
                return true;
            } else {
                conexao.rollback();
                return false;
            }
        }
    }

        @Override
    protected void executarRecarregar(ObjetoPersistente obj) throws SQLException {
        ObjetoPersistente objDoBanco = (ObjetoPersistente) this.buscarPeloOid(new Oid(obj.getId().toString()));
        if (objDoBanco != null) {
            copiarDados(obj, objDoBanco);
        }
    }

    protected abstract String obterNomesColunasInsert();
    protected abstract String obterInterrogacoesInsert();
    protected abstract void preencherParametrosInsert(PreparedStatement stm, Object obj) throws SQLException;
    protected abstract void definirId(Object obj, long id);
    protected abstract Object construirObjeto(ResultSet rs) throws SQLException;
    protected abstract String obterSetValuesUpdate();
    protected abstract int preencherParametrosUpdate(PreparedStatement stm, Object obj) throws SQLException;
    protected abstract void copiarDados(ObjetoPersistente destino, ObjetoPersistente origem);
}