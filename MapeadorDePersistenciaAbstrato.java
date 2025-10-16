package poo2;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public abstract class MapeadorDePersistenciaAbstrato implements IMapeador {

    private Map<String, ObjetoPersistente> cache = new HashMap<>();

    @Override
    public final Object obter(Oid oid) {
        if (cache.containsKey(oid.getString())) {
            System.out.println("[Cache] Objeto " + oid.getString() + " encontrado na cache.");
            return cache.get(oid.getString());
        }

        try {
            System.out.println("[Cache] Objeto " + oid.getString() + " não encontrado na cache. Buscando no banco de dados...");
            ObjetoPersistente objDoBanco = (ObjetoPersistente) buscarPeloOid(oid);
            
            if (objDoBanco != null) {
                cache.put(oid.getString(), objDoBanco);
            }
            return objDoBanco;
        } catch (SQLException e) {
            System.err.println("### ERRO DE SQL AO OBTER: " + e.getMessage());
            return null; 
        }
    }

    @Override
    public final boolean inserir(Object obj) {
        try {
            boolean resultado = executarInsert(obj);
            if (resultado) {
                ObjetoPersistente pObj = (ObjetoPersistente) obj;
                cache.put(pObj.getId().toString(), pObj);
            }
            return resultado;
        } catch (SQLException e) {
            System.err.println("### ERRO DE SQL AO INSERIR: " + e.getMessage());
            return false; 
        }
    }

    @Override
    public final boolean atualizar(Object obj) {
        try {
            boolean resultado = executarUpdate(obj);
            if (resultado) {
                ObjetoPersistente pObj = (ObjetoPersistente) obj;
                cache.put(pObj.getId().toString(), pObj);
            }
            return resultado;
        } catch (SQLException e) {
            System.err.println("### ERRO DE SQL AO ATUALIZAR: " + e.getMessage());
            return false; 
        }
    }

    @Override
    public final boolean excluir(Object obj) {
        try {
            boolean resultado = executarDelete(obj);
            if (resultado) {
                ObjetoPersistente pObj = (ObjetoPersistente) obj;
                cache.remove(pObj.getId().toString());
            }
            return resultado;
        } catch (SQLException e) {
            System.err.println("### ERRO DE SQL AO EXCLUIR: " + e.getMessage());
            return false;
        }
    }

    @Override
    public final void recarregar(ObjetoPersistente obj) {
        try {
            executarRecarregar(obj);
            cache.put(obj.getId().toString(), obj);
        } catch (SQLException e) {
            System.err.println("### ERRO DE SQL AO RECARREGAR: " + e.getMessage());
        }
    }

    protected abstract boolean executarInsert(Object obj) throws SQLException;
    protected abstract Object buscarPeloOid(Oid oid) throws SQLException;
    protected abstract boolean executarUpdate(Object obj) throws SQLException;
    protected abstract boolean executarDelete(Object obj) throws SQLException;
    protected abstract void executarRecarregar(ObjetoPersistente obj) throws SQLException; 
}