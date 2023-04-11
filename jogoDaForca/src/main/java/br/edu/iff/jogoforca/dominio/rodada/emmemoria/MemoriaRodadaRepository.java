package br.edu.iff.jogoforca.dominio.rodada.emmemoria;

import br.edu.iff.jogoforca.dominio.jogador.Jogador;
import br.edu.iff.jogoforca.dominio.rodada.Rodada;
import br.edu.iff.jogoforca.dominio.rodada.RodadaRepository;
import br.edu.iff.repository.RepositoryException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MemoriaRodadaRepository implements RodadaRepository{
    private static MemoriaRodadaRepository soleInstance = null;
    private List <Rodada> pool;

    private MemoriaRodadaRepository() {
        this.pool = new ArrayList<>();
    }  

    public static MemoriaRodadaRepository getSoleInstance() {
        if (soleInstance == null)
            soleInstance = new MemoriaRodadaRepository();
        return soleInstance;
    }   

    @Override
    public Rodada getPorId(Long id) {
       return this.pool.stream()
        .filter(rodada -> rodada.getId().equals(id))
        .findFirst()
        .orElse(null);
    }

    @Override
    public List<Rodada> getPorJogador(Jogador jogador) {
        return this.pool.stream()
        .filter(rodada -> rodada.getJogador().equals(jogador))
        .collect(Collectors.toList());
    }

    @Override
    public void inserir(Rodada rodada) throws RepositoryException {
    	 if (pool.contains(rodada)){
             throw new RepositoryException("Este tema já existe no banco");
         }
         this.pool.add(rodada);
    }

    @Override
    public void atualiza(Rodada rodada) throws RepositoryException {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public void remover(Rodada rodada) throws RepositoryException {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public Long getProximoId() {
        return Long.valueOf(this.pool.size() + 1);
    }
}
