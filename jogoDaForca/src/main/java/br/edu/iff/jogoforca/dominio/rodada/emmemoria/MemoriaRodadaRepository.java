package br.edu.iff.jogoforca.dominio.rodada.emmemoria;

import br.edu.iff.jogoforca.dominio.jogador.Jogador;
import br.edu.iff.jogoforca.dominio.rodada.Rodada;
import br.edu.iff.jogoforca.dominio.rodada.RodadaRepository;
import br.edu.iff.repository.RepositoryException;
import java.util.ArrayList;
import java.util.List;

public class MemoriaRodadaRepository implements RodadaRepository{
    private static MemoriaRodadaRepository soleInstance = null;
    private List <Rodada> pool;

    private MemoriaRodadaRepository() {
        pool = new ArrayList<>();
    }  

    public static MemoriaRodadaRepository getSoleInstance() {
        if (soleInstance == null)
            soleInstance = new MemoriaRodadaRepository();
        return soleInstance;
    }   

    @Override
    public Rodada getPorId(Long id) {
       return null; //Não há métodos estáticos em Rodada para esta busca
    }

    @Override
    public List<Rodada> getPorJogador(Jogador jogador) {
        return null; //Não há métodos estáticos em Rodada para esta busca
    }

    @Override
    public void inserir(Rodada rodada) throws RepositoryException {
        throw new UnsupportedOperationException("Not supported yet."); 
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
        throw new UnsupportedOperationException("Not supported yet."); 
    }
}
