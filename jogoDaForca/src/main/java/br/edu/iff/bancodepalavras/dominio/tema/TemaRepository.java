public interface TemaRepository {
    Tema getPorId(long id);
    Tema[] getPorNome(String nome);
    Tema[] getTodos();
    void inserir(Tema tema) throws RepositoryException;
    void atualizar(Tema tema) throws RepositoryException;
    void remover(Tema tema) throws RepositoryException;
}
