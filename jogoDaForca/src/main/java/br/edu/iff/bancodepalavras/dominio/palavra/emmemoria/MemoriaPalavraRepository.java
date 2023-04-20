package br.edu.iff.bancodepalavras.dominio.palavra.emmemoria;

import br.edu.iff.bancodepalavras.dominio.palavra.Palavra;
import br.edu.iff.bancodepalavras.dominio.palavra.PalavraRepository;
import br.edu.iff.bancodepalavras.dominio.tema.Tema;
import br.edu.iff.repository.RepositoryException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class MemoriaPalavraRepository implements PalavraRepository {

    private static MemoriaPalavraRepository soleInstance = null;
    private HashMap<Long, Palavra> pool;

    private MemoriaPalavraRepository() {
        this.pool = new HashMap<>();
    }

    public static MemoriaPalavraRepository getSoleInstance() {
        return soleInstance == null ? soleInstance = new MemoriaPalavraRepository() : soleInstance;
    }

    @Override
    public Palavra getPorId(Long id) {
        return this.pool.get(id);
    }

    @Override
    public List<Palavra> getPorTema(Tema tema) {
        List<Palavra> palavras = new ArrayList<Palavra>(pool.values());
        List<Palavra> palavrasPorTema = new ArrayList<>();
        for (Palavra p : palavras) {
            if (p.getTema().equals(tema)) {
                palavrasPorTema.add(p);
            }
        }
        return palavrasPorTema;
    }

    @Override
    public List<Palavra> getTodas() {
        return Collections.unmodifiableList(new ArrayList<Palavra>(pool.values()));
    }

    @Override
    public Palavra getPalavra(String palavra) {
        return pool.values()
                .stream()
                .filter(p -> p.comparar(palavra))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void inserir(Palavra palavra) throws RepositoryException {
        if (this.pool.containsKey(palavra.getId()) || this.pool.containsValue(palavra)) {
            throw new RepositoryException("Palavra já inserida no banco de dados");
        }
        this.pool.put(palavra.getId(), palavra);
    }

    @Override
    public void atualizar(Palavra palavra) throws RepositoryException {
        if (!this.pool.containsKey(palavra.getId())) {
            throw new RepositoryException("A palavra não exite no banco de dados");
        }
        this.pool.replace(palavra.getId(), palavra);
    }

    @Override
    public void remover(Palavra palavra) throws RepositoryException {
        if (!this.pool.containsKey(palavra.getId())) {
            throw new RepositoryException("A palavra não exite no banco de dados");
        }
        this.pool.remove(palavra.getId());
    }

    @Override
    public Long getProximoId() {        
        return Long.valueOf(this.pool.size()+1);
    }

}
