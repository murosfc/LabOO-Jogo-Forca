package br.edu.iff.jogoforca;

public class Aplicacao {
    private static final String[] TIPOS_REPOSITORY_FACTORY = {"memoria, relacional"};
    private static final String[] TIPOS_ELEMENTO_GRAFICO_FACTORY = {"texto", "imagem"};
    private static final String[] TIPOS_RODADA_FACTORY = {"sorteio"};
    
    private static Aplicacao soleInstance = null;
    
    private String tipoRepositoryFactory = TIPOS_REPOSITORY_FACTORY[0];
    private String tipoElementoGraficoFactory = TIPOS_ELEMENTO_GRAFICO_FACTORY[0];
    private String tipoRodadaFactory = TIPOS_RODADA_FACTORY[0];

    private Aplicacao() {
    }

    public static Aplicacao getSoleInstance() {
        if (soleInstance == null)
            soleInstance = new Aplicacao();
        return soleInstance;
    }    
}
