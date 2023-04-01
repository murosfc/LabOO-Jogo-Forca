package br.edu.iff.jogoforca.dominio.rodada;

import br.edu.iff.jogoforca.dominio.jogador.Jogador;
import br.edu.iff.jogoforca.dominio.jogador.JogadorNaoEncontradoException;
import br.edu.iff.jogoforca.dominio.jogador.JogadorRepository;
import br.edu.iff.repository.RepositoryException;

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

    public Rodada novaRodada(Long idJogador) throws JogadorNaoEncontradoException {
        Jogador jogador = jogadorRepository.getPorId(idJogador);
        return this.novaRodada(jogador);
    }

    public Rodada novaRodada(String nomeJogador) throws JogadorNaoEncontradoException {
        Jogador jogador = jogadorRepository.getPorNome(nomeJogador);
        return this.novaRodada(jogador);
    }

    public Rodada novaRodada(Jogador jogador) throws JogadorNaoEncontradoException {
        if (jogador == null) {
            throw new JogadorNaoEncontradoException("Jogador não encontrado!");
        }
        return rodadaFactory.getRodada(jogador);
    }

    public boolean salvaRodada(Rodada rodada) {
        try {
            rodadaRepository.inserir(rodada);
            return true;
        } catch (RepositoryException exception) {
            System.err.print(exception.getMessage());
            return false;
        }
    }

}
