package br.edu.iff.jogoforca.emmemoria;

import br.edu.iff.bancodepalavras.dominio.palavra.PalavraRepository;
import br.edu.iff.bancodepalavras.dominio.palavra.emmemoria.MemoriaPalavraRepository;
import br.edu.iff.bancodepalavras.dominio.tema.TemaRepository;
import br.edu.iff.bancodepalavras.dominio.tema.emmemoria.MemoriaTemaRepository;
import br.edu.iff.jogoforca.RepositoryFactory;
import br.edu.iff.jogoforca.dominio.jogador.JogadorRepository;
import br.edu.iff.jogoforca.dominio.jogador.emmemoria.MemoriaJogadorRepository;
import br.edu.iff.jogoforca.dominio.rodada.RodadaRepository;
import br.edu.iff.jogoforca.dominio.rodada.emmemoria.MemoriaRodadaRepository;

public class MemoriaRepositoryFactory implements RepositoryFactory{
    private static MemoriaRepositoryFactory soleInstance = null;

    private MemoriaRepositoryFactory() {
    }

    public static MemoriaRepositoryFactory getSoleInstance() {       
        return soleInstance == null ? soleInstance = new MemoriaRepositoryFactory() : soleInstance;
    }

    @Override
    public PalavraRepository getPalavraRepository() {
        return MemoriaPalavraRepository.getSoleInstance();
    }

    @Override
    public TemaRepository getTemaRepository() {
       return MemoriaTemaRepository.getSoleInstance();
    }

    @Override
    public RodadaRepository getRodadaRepository() {
        return MemoriaRodadaRepository.getSoleInstance();
    }

    @Override
    public JogadorRepository getJogadorRepository() {
       return MemoriaJogadorRepository.getSoleInstance();
    }   
}
