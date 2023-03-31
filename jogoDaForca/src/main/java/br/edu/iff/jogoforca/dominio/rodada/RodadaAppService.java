package br.edu.iff.jogoforca.dominio.rodada;

import br.edu.iff.jogoforca.dominio.jogador.Jogador;
import br.edu.iff.jogoforca.dominio.jogador.JogadorNaoEncontradoException;
import br.edu.iff.jogoforca.dominio.jogador.JogadorRepository;

public class RodadaAppService {
    private static RodadaAppService soleInstance = null;
    
    private RodadaFactory rodadaFactory;
    private RodadaRepository rodadaRepository;
    private JogadorRepository jogadorRepository;

    private RodadaAppService(RodadaFactory rodadaFactory, RodadaRepository rodadaRepository, JogadorRepository jogadorRepository) {
        this.rodadaFactory = rodadaFactory;
        this.rodadaRepository = rodadaRepository;
        this.jogadorRepository = jogadorRepository;
    }
    
    public static void createSoleInstance(RodadaFactory rodadaFactory, RodadaRepository rodadaRepository, JogadorRepository jogadorRepository) {
        new RodadaAppService(rodadaFactory, rodadaRepository, jogadorRepository);
    }    

    public static RodadaAppService getSoleInstance() {
        return soleInstance == null ? new RodadaAppService(null, null, null) : soleInstance;
    }
    
    public Rodada novaRodada(Long idJogador) throws JogadorNaoEncontradoException{
        Jogador jogador = jogadorRepository.getPorId(idJogador);
        if (jogador == null){
            throw new JogadorNaoEncontradoException("Jogador não encontrado com a id: "+ idJogador);
       }
        return null; // TODO retornar rodada
    }
    
    public Rodada novaRodada(String nomeJogador) throws JogadorNaoEncontradoException{
        Jogador jogador = jogadorRepository.getPorNome(nomeJogador);
        if (jogador == null){
            throw new JogadorNaoEncontradoException("Jogador " + nomeJogador + " não encontrado!");
       }
        return null; // TODO retornar rodada
    }
    
    public Rodada novaRodada(Jogador jogador){       
        return null; // TODO retornar rodada
    }
    
}
