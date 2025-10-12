package poo2;

import java.sql.SQLException;

public abstract class MapeadorDePersistenciaAbstrato implements IMapeador {

    @Override
    public final boolean inserir(Object obj) {
        try {
            return executarInsert(obj);
        } catch (SQLException e) {
            throw new RuntimeException("Erro de SQL ao inserir", e);
        }
    }

    @Override
    public final Object obter(Oid oid) {
        try {
            return buscarPeloOid(oid);
        } catch (SQLException e) {
            throw new RuntimeException("Erro de SQL ao obter", e);
        }
    }

    @Override
    public final boolean atualizar(Object obj) {
        try {
            return executarUpdate(obj);
        } catch (SQLException e) {
            throw new RuntimeException("Erro de SQL ao atualizar", e);
        }
    }

    @Override
    public final boolean excluir(Object obj) {
        try {
            return executarDelete(obj);
        } catch (SQLException e) {
            throw new RuntimeException("Erro de SQL ao excluir", e);
        }
    }

    public final void recarregar(ObjetoPersistente obj) {
        try {
            executarRecarregar(obj);
        } catch (SQLException e) {
            throw new RuntimeException("Erro de SQL ao recarregar", e);
        }
    }
    protected abstract boolean executarInsert(Object obj) throws SQLException;
    protected abstract Object buscarPeloOid(Oid oid) throws SQLException;
    protected abstract boolean executarUpdate(Object obj) throws SQLException; 
    protected abstract boolean executarDelete(Object obj) throws SQLException;
    protected abstract void executarRecarregar(ObjetoPersistente obj) throws SQLException; 
}