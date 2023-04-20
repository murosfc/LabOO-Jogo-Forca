package br.edu.iff.jogoforca.dominio.rodada;

import br.edu.iff.bancodepalavras.dominio.palavra.PalavraRepository;
import br.edu.iff.bancodepalavras.dominio.tema.TemaRepository;
import br.edu.iff.factory.EntityFactory;

public abstract class RodadaFactoryImpl extends EntityFactory implements RodadaFactory {
    
    private TemaRepository temaRepository;
    private PalavraRepository palavraRepository;

    protected RodadaFactoryImpl(RodadaRepository rodadaRepository, TemaRepository temaRepository, PalavraRepository palavraRepository) {
        super(rodadaRepository);        
        this.temaRepository = temaRepository;
        this.palavraRepository = palavraRepository;
    }    
    
    protected RodadaRepository getRodadaRepository() {
        return (RodadaRepository) this.repository;
    }

    protected  TemaRepository getTemaRepository() {
        return this.temaRepository;
    }

    protected  PalavraRepository getPalavraRepository() {
        return this.palavraRepository;
    }
}
