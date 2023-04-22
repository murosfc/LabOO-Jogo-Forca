package br.edu.iff.jogoforca;

import br.edu.iff.bancodepalavras.dominio.letra.LetraFactory;
import br.edu.iff.bancodepalavras.dominio.palavra.Palavra;
import br.edu.iff.bancodepalavras.dominio.palavra.PalavraFactory;
import br.edu.iff.bancodepalavras.dominio.palavra.PalavraFactoryImpl;
import br.edu.iff.bancodepalavras.dominio.tema.TemaFactory;
import br.edu.iff.bancodepalavras.dominio.tema.TemaFactoryImpl;
import br.edu.iff.jogoforca.dominio.boneco.BonecoFactory;
import br.edu.iff.jogoforca.dominio.jogador.JogadorFactory;
import br.edu.iff.jogoforca.dominio.jogador.JogadorFactoryImpl;
import br.edu.iff.jogoforca.dominio.rodada.Rodada;
import br.edu.iff.jogoforca.dominio.rodada.RodadaFactory;
import br.edu.iff.jogoforca.dominio.rodada.sorteio.RodadaSorteioFactory;
import br.edu.iff.jogoforca.embdr.BDRRepositoryFactory;
import br.edu.iff.jogoforca.emmemoria.MemoriaRepositoryFactory;
import br.edu.iff.jogoforca.imagem.ElementoGraficoImagemFactory;
import br.edu.iff.jogoforca.texto.ElementoGraficoTextoFactory;

public class Aplicacao {

    private static final String[] TIPOS_REPOSITORY_FACTORY = {"memoria, relacional"};
    private static final String[] TIPOS_ELEMENTO_GRAFICO_FACTORY = {"texto", "imagem"};
    private static final String[] TIPOS_RODADA_FACTORY = {"sorteio"};

    private static Aplicacao soleInstance = null;

    private String tipoRepositoryFactory = TIPOS_REPOSITORY_FACTORY[0];
    private String tipoElementoGraficoFactory = TIPOS_ELEMENTO_GRAFICO_FACTORY[1];
    private String tipoRodadaFactory = TIPOS_RODADA_FACTORY[0];

    private RepositoryFactory repositoryFactory;
    private ElementoGraficoFactory elementoGraficoFactory;
    private RodadaFactory rodadaFactory;

    private Aplicacao() {
        configurar();
    }

    public static Aplicacao getSoleInstance() {
        return soleInstance == null ? soleInstance = new Aplicacao() : soleInstance;
    }

    public void configurar() {
        if (this.tipoRepositoryFactory.equalsIgnoreCase(TIPOS_REPOSITORY_FACTORY[0])) {
            this.repositoryFactory = MemoriaRepositoryFactory.getSoleInstance();
        } else if (this.tipoRepositoryFactory.equalsIgnoreCase(TIPOS_REPOSITORY_FACTORY[1])) {
            this.repositoryFactory = BDRRepositoryFactory.getSoleInstance();
        }

        if (this.tipoElementoGraficoFactory.equalsIgnoreCase(TIPOS_ELEMENTO_GRAFICO_FACTORY[0])) {
            this.elementoGraficoFactory = ElementoGraficoTextoFactory.getSoleInstance();
        } else if (this.tipoElementoGraficoFactory.equalsIgnoreCase(TIPOS_ELEMENTO_GRAFICO_FACTORY[1])) {
            this.elementoGraficoFactory = ElementoGraficoImagemFactory.getSoleInstance();
        }      
        
        if (this.tipoRodadaFactory.equalsIgnoreCase(TIPOS_RODADA_FACTORY[0])) {            
            RodadaSorteioFactory.createSoleInstance(this.repositoryFactory.getRodadaRepository(),
                    this.repositoryFactory.getTemaRepository(),
                    this.repositoryFactory.getPalavraRepository());
            this.rodadaFactory = RodadaSorteioFactory.getSoleInstance();           
        }

        TemaFactoryImpl.createSoleInstance(this.repositoryFactory.getTemaRepository());
        PalavraFactoryImpl.createSoleInstance(this.repositoryFactory.getPalavraRepository());
        JogadorFactoryImpl.createSoleInstance(this.repositoryFactory.getJogadorRepository());
        Palavra.setLetraFactory(elementoGraficoFactory);
        Rodada.setBonecoFactory(elementoGraficoFactory);
    }

    public String getTipoRepositoryFactory() {
        return tipoRepositoryFactory;
    }

    public void setTipoRepositoryFactory(String tipoRepositoryFactory) {
        this.tipoRepositoryFactory = tipoRepositoryFactory;
    }

    public RepositoryFactory getRepositoryFactory() {
        return repositoryFactory;
    }

    private String getTipoElementoGraficoFactory() {
        return tipoElementoGraficoFactory;
    }

    public void setTipoElementoGraficoFactory(String tipoElementoGraficoFactory) {
        this.tipoElementoGraficoFactory = tipoElementoGraficoFactory;
    }

    public ElementoGraficoFactory getElementoGraficoFactory() {
        return elementoGraficoFactory;
    }

    public BonecoFactory getBonecoFactory() {
        return this.getElementoGraficoFactory();
    }

    public LetraFactory getLetraFactory() {
        return this.getElementoGraficoFactory();
    }

    public String getTipoRodadaFactory() {
        return tipoRodadaFactory;
    }

    public void setTipoRodadaFactory(String tipoRodadaFactory) {
        this.tipoRodadaFactory = tipoRodadaFactory;
    }

    public RodadaFactory getRodadaFactory() {
        return this.rodadaFactory;
    }

    public TemaFactory getTemaFactory() {
        return TemaFactoryImpl.getSoleInstance();
    }

    public PalavraFactory getPalavraFactory() {
        return PalavraFactoryImpl.getSoleInstance();
    }

    public JogadorFactory getJogadorFactory() {
        return JogadorFactoryImpl.getSoleInstance();
    }
}
