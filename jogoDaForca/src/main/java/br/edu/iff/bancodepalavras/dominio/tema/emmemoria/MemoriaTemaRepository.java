package br.edu.iff.bancodepalavras.dominio.tema.emmemoria;

import br.edu.iff.bancodepalavras.dominio.tema.Tema;
import br.edu.iff.bancodepalavras.dominio.tema.TemaRepository;
import br.edu.iff.repository.RepositoryException;
import java.util.List;

public class MemoriaTemaRepository implements TemaRepository {
    private static MemoriaTemaRepository soleInstance = null;

    private MemoriaTemaRepository() {
        // construtor privado para evitar que outras instâncias sejam criadas
    }

    public static MemoriaTemaRepository getSoleInstance() {
        if (soleInstance == null) {
            soleInstance = new MemoriaTemaRepository();
        }
        return soleInstance;
    }

    // implementação dos métodos da interface TemaRepository
   
    @Override
    public List<Tema> getPorNome(String nome) {
        // implementação
    }
    
     @Override
    public Tema getPorId(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Tema> getTodos() {
        // implementação
    }

    @Override
    public void inserir(Tema tema) throws RepositoryException {
        // implementação
    }

    @Override
    public void atualizar(Tema tema) throws RepositoryException {
        // implementação
    }

    @Override
    public void remover(Tema tema) throws RepositoryException {
        // implementação
    }

   
}
