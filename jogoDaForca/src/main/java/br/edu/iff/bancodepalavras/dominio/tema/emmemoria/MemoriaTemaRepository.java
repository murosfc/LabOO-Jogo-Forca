package br.edu.iff.bancodepalavras.dominio.tema.emmemoria;

import br.edu.iff.bancodepalavras.dominio.tema.Tema;
import br.edu.iff.bancodepalavras.dominio.tema.TemaRepository;
import br.edu.iff.repository.RepositoryException;
import java.util.ArrayList;
import java.util.List;

public class MemoriaTemaRepository implements TemaRepository {

    private static MemoriaTemaRepository soleInstance = null;
    private List<Tema> pool;

    private MemoriaTemaRepository() {
        this.pool = new ArrayList<>();
    }

    public static MemoriaTemaRepository getSoleInstance() {
        return soleInstance == null ? soleInstance = new MemoriaTemaRepository() : soleInstance;
    }
    // implementação dos métodos da interface TemaRepository

    @Override
    public List<Tema> getPorNome(String nome) {
        List<Tema> temaBuscado = new ArrayList<>();
        for (Tema tema : pool){
            if(tema.getNome().equalsIgnoreCase(nome))
                temaBuscado.add(tema);
        }
        return temaBuscado;
    }

    @Override
    public Tema getPorId(Long id) {      
        for (Tema tema : pool){
            if(tema.getId() == id)
                return tema;
        }
        return null; 
    }

    @Override
    public List<Tema> getTodos() {
        return this.pool;
    }

    @Override
    public void inserir(Tema tema) throws RepositoryException {
        if (pool.contains(tema)){
            throw new RepositoryException("Este tema já existe no banco");
        }
        this.pool.add(tema);
    }

    @Override
    public void atualizar(Tema tema) throws RepositoryException {
         if (pool.contains(tema)){
            throw new RepositoryException("Não houve atualização do tema");
        }
        this.pool.add(tema);
    }

    @Override
    public void remover(Tema tema) throws RepositoryException {
        if (!pool.contains(tema)){
            throw new RepositoryException("Este tema não existe no banco");
        }
        this.pool.remove(tema);
    }

    @Override
    public Long getProximoId() {
        return Long.valueOf(this.pool.size() + 1);
    }

}
