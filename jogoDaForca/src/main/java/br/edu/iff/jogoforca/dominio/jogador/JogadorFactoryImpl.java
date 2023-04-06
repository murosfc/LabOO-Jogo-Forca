package br.edu.iff.jogoforca.dominio.jogador;

import br.edu.iff.factory.EntityFactory;
import br.edu.iff.repository.Repository;

public class JogadorFactoryImpl extends EntityFactory implements JogadorFactory{

    private static JogadorFactoryImpl soleInstance;    
    
    private JogadorFactoryImpl(JogadorRepository repository) {
        super(repository);        
    }    

    public static void createSoleInstance(JogadorRepository repository){
        if (soleInstance == null)
            soleInstance = new JogadorFactoryImpl(repository);
    }

    public static JogadorFactoryImpl getSoleInstance() {
        return soleInstance;
    }

    public JogadorFactoryImpl(Repository repository) {
        super(repository);
    }

    @Override
    public Jogador getJogador(String nome) {
        return Jogador.criar(getProximoId(), nome);
    }
}
