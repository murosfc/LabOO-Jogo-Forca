package br.edu.iff.jogoforca.dominio.rodada.emmemoria;

import br.edu.iff.bancodepalavras.dominio.palavra.Palavra;
import br.edu.iff.jogoforca.dominio.jogador.Jogador;
import br.edu.iff.jogoforca.dominio.rodada.Rodada;
import br.edu.iff.jogoforca.dominio.rodada.RodadaRepository;
import br.edu.iff.repository.RepositoryException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class MemoriaRodadaRepository implements RodadaRepository {

    private static MemoriaRodadaRepository soleInstance = null;
    private HashMap<Long, Rodada> pool;

    private MemoriaRodadaRepository() {
        this.pool = new HashMap<>();
    }

    public static MemoriaRodadaRepository getSoleInstance() {
        return soleInstance == null ? soleInstance = new MemoriaRodadaRepository() : soleInstance;
    }

    @Override
    public Rodada getPorId(Long id) {
        return this.pool.get(id);
    }

    @Override
    public List<Rodada> getPorJogador(Jogador jogador) {
        List<Rodada> rodadasDisponiveis = new ArrayList<Rodada>(pool.values());
        List<Rodada> rodadasEncontradas = new ArrayList<>();
        for (Rodada r : rodadasDisponiveis){
            if (r.getJogador().equals(jogador)){
                rodadasEncontradas.add(r);
            }
        }
        return rodadasEncontradas;
    }

    @Override
    public void inserir(Rodada rodada) throws RepositoryException {
        if (this.pool.containsKey(rodada.getId()) || this.pool.containsValue(rodada)) {
            throw new RepositoryException("Esta rodada já existe no repositório");
        }
        this.pool.put(rodada.getId(), rodada);
    }

    @Override
    public void atualiza(Rodada rodada) throws RepositoryException {
        if (!this.pool.containsKey(rodada.getId())) {
            throw new RepositoryException("Rodada não existe no repositório");
        }
        this.pool.replace(rodada.getId(), rodada);
    }

    @Override
    public void remover(Rodada rodada) throws RepositoryException {
        if (!this.pool.containsKey(rodada.getId())) {
            throw new RepositoryException("Rodada não existe no repositório");
        }
        this.pool.remove(rodada.getId());
    }

    @Override
    public Long getProximoId() {
        return Long.valueOf(this.pool.size() + 1);
    }
}
