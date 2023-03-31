public class PalavraFactoryImpl extends EntityFactory implements PalavraFactory {

    private static PalavraFactoryImpl soleInstance;

    private final PalavraRepository repository;

    private PalavraFactoryImpl(PalavraRepository repository) {
        this.repository = repository;
    }

    public static void createSoleInstance(PalavraRepository repository) {
        if (soleInstance == null) {
            soleInstance = new PalavraFactoryImpl(repository);
        }
    }

    public static PalavraFactoryImpl getSoleInstance() {
        return soleInstance;
    }

    private PalavraRepository getPalavraRepository() {
        return repository;
    }

    public Palavra getPalavra(String palavra, Tema tema) {
        long id = getPalavraRepository().getProximoId();
        Palavra p = new Palavra(id, palavra, tema);
        try {
            getPalavraRepository().inserir(p);
        } catch (RepositoryException e) {
            // handle exception
        }
        return p;
    }
}
