public class MemoriaPalavraRepository implements PalavraRepository {
    
    private static MemoriaPalavraRepository soleInstance = new MemoriaPalavraRepository();
    
    private MemoriaPalavraRepository() {}
    
    public static MemoriaPalavraRepository getSoleInstance() {
        return soleInstance;
    }
    
    // implementação dos métodos da interface PalavraRepository
    
    @Override
    public Palavra getPorId(long id) {
        // implementação do método
    }
    
    @Override
    public Palavra[] getPorTema(Tema tema) {
        // implementação do método
    }
    
    @Override
    public Palavra[] getTodas() {
        // implementação do método
    }
    
    @Override
    public Palavra getPalavra(String palavra) {
        // implementação do método
    }
    
    @Override
    public void inserir(Palavra palavra) throws RepositoryException {
        // implementação do método
    }
    
    @Override
    public void atualizar(Palavra palavra) throws RepositoryException {
        // implementação do método
    }
    
    @Override
    public void remover(Palavra palavra) throws RepositoryException {
        // implementação do método
    }
    
}
