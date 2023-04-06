package br.edu.iff.bancodepalavras.dominio.palavra.emmemoria;

import br.edu.iff.bancodepalavras.dominio.palavra.Palavra;
import br.edu.iff.bancodepalavras.dominio.palavra.PalavraFactoryImpl;
import br.edu.iff.bancodepalavras.dominio.palavra.PalavraRepository;
import br.edu.iff.bancodepalavras.dominio.tema.Tema;
import br.edu.iff.repository.RepositoryException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class MemoriaPalavraRepository implements PalavraRepository {
    
    private static MemoriaPalavraRepository soleInstance = new MemoriaPalavraRepository();
    private List<Palavra> pool;
    
    private MemoriaPalavraRepository() {
        this.pool = new ArrayList<>();
    }
    
    public static MemoriaPalavraRepository getSoleInstance() {
        return soleInstance;
    }

    @Override
    public Palavra getPorId(Long id) {
        return this.pool.stream()
                    .filter(p -> p.getId() == id)
                    .findFirst()
                    .orElse(null);
    }

    @Override
    public List<Palavra> getPorTema(Tema tema) {
        return this.pool.stream()
                  .filter(palavra -> palavra.getTema().equals(tema))
                  .collect(Collectors.toList());
    }

    @Override
    public List<Palavra> getTodas() {
        return Collections.unmodifiableList(pool);
    }

    @Override
    public Palavra getPalavra(String palavra) {
        return this.pool.stream()
                    .filter(p -> p.equals(palavra))
                    .findFirst()
                    .orElse(null);
    }

    @Override
    public void inserir(Palavra palavra) throws RepositoryException {        
        if (this.pool.contains(palavra)){
            throw new RepositoryException("Palavra já inserida no banco de dados");
        }
        this.pool.add(palavra);
    }

    @Override
    public void atualizar(Palavra palavra) throws RepositoryException {
        if (!this.pool.contains(palavra)){
            throw new RepositoryException("A palavra não exite no banco de dados");
        }
        pool.remove(palavra);
        pool.add(palavra);
    }    

    @Override
    public void remover(Palavra palavra) throws RepositoryException {
        if (!this.pool.contains(palavra)){
            throw new RepositoryException("A palavra não exite no banco de dados");
        }
        pool.remove(palavra);        
    }

    @Override
    public Long getProximoId() {        
        return Long.valueOf( this.pool.size() + 1);
    }
    
    
   
    
}
