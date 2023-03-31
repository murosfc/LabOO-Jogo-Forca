public class BDRPalavraRepository implements PalavraRepository {

    private static BDRPalavraRepository soleInstance = null;

    private BDRPalavraRepository() {
        // construtor privado para implementar o Singleton
    }

    public static BDRPalavraRepository getSoleInstance() {
        if (soleInstance == null) {
            soleInstance = new BDRPalavraRepository();
        }
        return soleInstance;
    }

    @Override
    public Palavra getPorId(long id) {
        // implementação para obter uma palavra pelo ID a partir do Banco de Dados Relacional
        return null;
    }

    @Override
    public Palavra[] getPorTema(Tema tema) {
        // implementação para obter as palavras de um tema a partir do Banco de Dados Relacional
        return null;
    }

    @Override
    public Palavra[] getTodas() {
        // implementação para obter todas as palavras do Banco de Dados Relacional
        return null;
    }

    @Override
    public Palavra getPalavra(String palavra) {
        // implementação para obter uma palavra a partir do Banco de Dados Relacional
        return null;
    }

    @Override
    public void inserir(Palavra palavra) throws RepositoryException {
        // implementação para inserir uma palavra no Banco de Dados Relacional
    }

    @Override
    public void atualizar(Palavra palavra) throws RepositoryException {
        // implementação para atualizar uma palavra no Banco de Dados Relacional
    }

    @Override
    public void remover(Palavra palavra) throws RepositoryException {
        // implementação para remover uma palavra do Banco de Dados Relacional
    }
}
