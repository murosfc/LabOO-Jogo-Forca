package br.edu.iff.jogoforca.dominio.jogador.embdr;

import br.edu.iff.jogoforca.dominio.jogador.Jogador;
import br.edu.iff.jogoforca.dominio.jogador.JogadorRepository;
import br.edu.iff.repository.RepositoryException;

public class BDRJogadorRepository implements JogadorRepository {

    private static BDRJogadorRepository soleInstance;

    public BDRJogadorRepository getSoleInstance(){ return soleInstance; }

    private BDRJogadorRepository() {}

    @Override
    public Jogador getPorId(Long id) {
        return null;
    }

    @Override
    public Jogador getPorNome(String nome) {
        return null;
    }   

    @Override
    public Long getProximoId() {
         return null;
    }

    @Override
    public void inserir(Jogador jogador) throws RepositoryException {
    }

    @Override
    public void atualizar(Jogador jogador) throws RepositoryException {
    }

    @Override
    public void remover(Jogador jogador) throws RepositoryException {
    }
}
