public class TemaFactoryImpl implements TemaFactory {
    private static TemaFactoryImpl soleInstance;
    private TemaRepository repository;

    private TemaFactoryImpl(TemaRepository repository) {
        this.repository = repository;
    }

    public static void createSoleInstance(TemaRepository repository) {
        if (soleInstance == null) {
            soleInstance = new TemaFactoryImpl(repository);
        }
    }

    public static TemaFactoryImpl getSoleInstance() {
        return soleInstance;
    }

    public Tema getTema(String nome) {
        Tema tema = repository.getPorNome(nome);
        if (tema == null) {
            long id = EntityFactory.getProximoId();
            tema = new Tema(id, nome);
        }
        return tema;
    }

    private TemaRepository getTemaRepository() {
        return repository;
    }
}
