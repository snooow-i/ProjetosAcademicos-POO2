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

    protected abstract boolean executarInsert(Object obj) throws SQLException;
    protected abstract Object buscarPeloOid(Oid oid) throws SQLException;

    @Override
    public boolean atualizar(Object obj) {
        System.out.println("Atualizar não implementado.");
        return false;
    }

    @Override
    public boolean excluir(Object obj) {
        System.out.println("Excluir não implementado.");
        return false;
    }
}