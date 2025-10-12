package poo2;

import java.io.File;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

public class FabricaDeMapeador {

    private static FabricaDeMapeador instancia = null;
    private Map<String, IMapeador> mapeadores;

    private FabricaDeMapeador() {
        mapeadores = new HashMap<String, IMapeador>();
        montarColecaoMapeadores();
    }

    public static FabricaDeMapeador obterInstancia() {
        if (instancia == null) {
            instancia = new FabricaDeMapeador();
        }
        return instancia;
    }

    private void montarColecaoMapeadores() {
        File f = new File("configuracao/configuracao.xml");
        SAXBuilder builder = new SAXBuilder();
        
        try {
            Document doc = builder.build(f);
            Element root = (Element) doc.getRootElement();
            List<Element> listaMapeadores = root.getChildren("mapeador");
            
            for (Element mapeadorEl : listaMapeadores) {
                String chave = mapeadorEl.getChildText("chave");
                String nomeClasseMapa = mapeadorEl.getChildText("mapa");
                String nomeTabela = mapeadorEl.getChildText("tabela");
                
                System.out.println("[FabricaDeMapeador] Lendo configuração para a chave: " + chave);

                Class<?> classeMapeador = Class.forName(nomeClasseMapa);
                
                Constructor<?> construtor = classeMapeador.getConstructor(String.class);
                
                IMapeador novoMapeador = (IMapeador) construtor.newInstance(nomeTabela);
                
                mapeadores.put(chave, novoMapeador);
                System.out.println("   > Mapeador '" + nomeClasseMapa + "' carregado com sucesso.");
            }
            
        } catch (Exception e) {
            throw new RuntimeException("Erro ao ler o ficheiro de configuração dos mapeadores.", e);
        }
    }

    public IMapeador obterMapeador(String classeDePersistencia) {
        return mapeadores.get(classeDePersistencia);
    }
}