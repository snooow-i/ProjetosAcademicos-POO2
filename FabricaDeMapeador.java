package br.edu.faculdade.poo2;

import java.util.HashMap;
import java.util.Map;

public class FabricaDeMapeador {

    private static FabricaDeMapeador instancia = null;
    
    private Map<String, IMapeador> mapeadores;

    private FabricaDeMapeador() {
        this.mapeadores = new HashMap<>();
        

        mapeadores.put(Aluno.class.getSimpleName(), new MapeadorBDRAluno());
    }

    public static FabricaDeMapeador obterInstancia() {
        if (instancia == null) {
            instancia = new FabricaDeMapeador();
        }
        return instancia;
    }

    public IMapeador obterMapeador(String nomeClasse) {
        return mapeadores.get(nomeClasse);
    }
}
