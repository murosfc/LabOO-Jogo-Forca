public class BDRTemaRepository implements TemaRepository {
    private static BDRTemaRepository soleInstance;
    
    private BDRTemaRepository() {
        // construtor privado para impedir a criação de instâncias fora da classe
    }
    
    public static synchronized BDRTemaRepository getSoleInstance() {
        if (soleInstance == null) {
            soleInstance = new BDRTemaRepository();
        }
        return soleInstance;
    }
    
    public Tema getPorId(long id) {
        // implementação da busca por id em um banco de dados relacional
    }
    
    public Tema[] getPorNome(String nome) {
        // implementação da busca por nome em um banco de dados relacional
    }
    
    public Tema[] getTodos() {
        // implementação da busca de todos os temas em um banco de dados relacional
    }
    
    public void inserir(Tema tema) throws RepositoryException {
        // implementação da inserção de um tema em um banco de dados relacional
    }
    
    public void atualizar(Tema tema) throws RepositoryException {
        // implementação da atualização de um tema em um banco de dados relacional
    }
    
    public void remover(Tema tema) throws RepositoryException {
        // implementação da remoção de um tema em um banco de dados relacional
    }
}
