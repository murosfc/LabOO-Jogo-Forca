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
    public Tema getPorId(long id) {
        // implementação
    }

    @Override
    public Tema[] getPorNome(String nome) {
        // implementação
    }

    @Override
    public Tema[] getTodos() {
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
