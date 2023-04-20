package br.edu.iff.jogoforca.dominio.jogador.emmemoria;

import br.edu.iff.jogoforca.dominio.jogador.Jogador;
import br.edu.iff.jogoforca.dominio.jogador.JogadorRepository;
import br.edu.iff.repository.RepositoryException;
import java.util.HashMap;

public class MemoriaJogadorRepository implements JogadorRepository {

    private static MemoriaJogadorRepository soleInstance = null;

    private HashMap<Long, Jogador> pool;

    private MemoriaJogadorRepository() {
        this.pool = new HashMap<>();
    }

    public static MemoriaJogadorRepository getSoleInstance() {
        return soleInstance == null ? soleInstance = new MemoriaJogadorRepository() : soleInstance;
    }

    @Override
    public Jogador getPorId(Long id) {
        return this.pool.get(id);
    }

    @Override
    public Jogador getPorNome(String nome) {
         return pool.values()
                .stream()
                .filter(j -> j.getNome().equals(nome))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void inserir(Jogador jogador) throws RepositoryException {
        if (this.pool.containsKey(jogador.getId())) {
            throw new RepositoryException("O jogador já existe no banco de dados");
        }
        this.pool.put(jogador.getId(), jogador);
    }

    @Override
    public void atualizar(Jogador jogador) throws RepositoryException {
        if (!this.pool.containsKey(jogador.getId())) {
            throw new RepositoryException("O jogador não existe no banco de dados");
        }
        this.pool.replace(jogador.getId(), jogador);
    }

    @Override
    public void remover(Jogador jogador) throws RepositoryException {
        if (!this.pool.containsKey(jogador.getId())) {
            throw new RepositoryException("O jogador não existe no banco de dados");
        }
        this.pool.remove(jogador);
    }

    @Override
    public Long getProximoId() {
        return Long.valueOf(this.pool.size() + 1);
    }

}
