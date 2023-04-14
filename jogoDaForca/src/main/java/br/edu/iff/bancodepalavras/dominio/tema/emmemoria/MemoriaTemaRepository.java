package br.edu.iff.bancodepalavras.dominio.tema.emmemoria;

import br.edu.iff.bancodepalavras.dominio.tema.Tema;
import br.edu.iff.bancodepalavras.dominio.tema.TemaRepository;
import br.edu.iff.repository.RepositoryException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class MemoriaTemaRepository implements TemaRepository {

    private static MemoriaTemaRepository soleInstance = null;
    private HashMap<Long, Tema> pool;
    
    private MemoriaTemaRepository() {
        this.pool = new HashMap<>();
    }

    public static MemoriaTemaRepository getSoleInstance() {
        return soleInstance == null ? soleInstance = new MemoriaTemaRepository() : soleInstance;
    }
    // implementação dos métodos da interface TemaRepository
    
    @Override
    public Tema getPorId(Long id) {      
        return this.pool.get(id);
    }
    
    @Override
    public List<Tema> getPorNome(String nome) {
        List<Tema> temasdisponiveis =  new ArrayList<Tema>(this.pool.values());
        List<Tema> temaBuscado =  new ArrayList<>();
        for (Tema tema : temasdisponiveis){
            if(tema.getNome().equalsIgnoreCase(nome))
                temaBuscado.add(tema);
        }
        return temaBuscado;
    }

    @Override
    public List<Tema> getTodos() {
        return Collections.unmodifiableList(new ArrayList<Tema>(pool.values()));
    }

    @Override
    public void inserir(Tema tema) throws RepositoryException {
        if (this.pool.containsKey(tema.getId()) || this.pool.containsValue(tema)){
            throw new RepositoryException("Este tema já existe no banco");
        }
        this.pool.put(tema.getId(), tema);
    }

    @Override
    public void atualizar(Tema tema) throws RepositoryException {
         if (!this.pool.containsKey(tema.getId())){
            throw new RepositoryException("Tema não existe no repositório");
        }
        this.pool.replace(tema.getId(), tema);
    }

    @Override
    public void remover(Tema tema) throws RepositoryException {
        if (!this.pool.containsKey(tema.getId())){
            throw new RepositoryException("Tema não existe no repositório");
        }
        this.pool.remove(tema.getId());
    }

    @Override
    public Long getProximoId() {
        return Long.valueOf(this.pool.size() + 1);
    }

}
