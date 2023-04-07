package br.edu.iff.jogoforca.dominio.jogador.emmemoria;

import br.edu.iff.jogoforca.dominio.jogador.Jogador;
import br.edu.iff.jogoforca.dominio.jogador.JogadorRepository;
import br.edu.iff.repository.RepositoryException;
import java.util.ArrayList;
import java.util.List;

public class MemoriaJogadorRepository implements JogadorRepository {

    private static MemoriaJogadorRepository soleInstance;

    List<Jogador> pool;

    private MemoriaJogadorRepository() {
        this.pool = new ArrayList<>();
    }

    public static MemoriaJogadorRepository getSoleInstance() {
        return soleInstance == null ? new MemoriaJogadorRepository() : soleInstance;
    }

    @Override
    public Jogador getPorId(Long id) {
        return this.pool.stream()
                .filter(j -> j.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public Jogador getPorNome(String nome) {
        return this.pool.stream()
                .filter(j -> j.getNome().equals(nome))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void inserir(Jogador jogador) throws RepositoryException {
        if (this.pool.contains(jogador)) {
            throw new RepositoryException("O jogador já existe no banco de dados");
        }
        this.pool.add(jogador);
    }

    @Override
    public void atualizar(Jogador jogador) throws RepositoryException {
        if (!this.pool.contains(jogador)) {
            throw new RepositoryException("O jogador não existe no banco de dados");
        }
        this.pool.remove(jogador);
        this.pool.add(jogador);
    }

    @Override
    public void remover(Jogador jogador) throws RepositoryException {
        if (!this.pool.contains(jogador)) {
            throw new RepositoryException("O jogador não existe no banco de dados");
        }
        this.pool.remove(jogador);
    }

    @Override
    public Long getProximoId() {
        return Long.valueOf(this.pool.size() + 1);
    }

}
