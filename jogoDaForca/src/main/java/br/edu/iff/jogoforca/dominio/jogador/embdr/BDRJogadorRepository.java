package br.edu.iff.jogoforca.dominio.jogador.embdr;

import br.edu.iff.jogoforca.dominio.jogador.Jogador;
import br.edu.iff.jogoforca.dominio.jogador.JogadorRepository;
import br.edu.iff.repository.RepositoryException;

public class BDRJogadorRepository implements JogadorRepository {

    private static BDRJogadorRepository soleInstance;

    public BDRJogadorRepository getSoleInstance(){ return soleInstance; }

    private BDRJogadorRepository() {}

    @Override
    public Jogador getPorId(long id) {
        return null;
    }

    @Override
    public Jogador getPorNome(String nome) {
        return null;
    }

    @Override
    public Jogador inserir(Jogador jogador) throws RepositoryException {
        return null;
    }

    @Override
    public Jogador atualizar(Jogador jogador) throws RepositoryException {
        return null;
    }

    @Override
    public Jogador remover(Jogador jogador) throws RepositoryException {
        return null;
    }
}
