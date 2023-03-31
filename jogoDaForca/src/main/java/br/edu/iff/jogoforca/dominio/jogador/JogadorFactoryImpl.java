package br.edu.iff.jogoforca.dominio.jogador;

public class JogadorFactoryImpl implements JogadorFactory{

    private static JogadorFactoryImpl soleInstance;
    private final JogadorRepository repository;

    private JogadorFactoryImpl(JogadorRepository repository) {
        this.repository = repository;
    }

    public static void createSoleInstance(JogadorRepository repository){
        if (soleInstance == null)
            soleInstance = new JogadorFactoryImpl(repository);
    }

    public static synchronized JogadorFactoryImpl getSoleInstance() {
        return soleInstance;
    }

    @Override
    public Jogador getJogador(String nome) {
        return repository.getPorNome(nome);
    }
}
