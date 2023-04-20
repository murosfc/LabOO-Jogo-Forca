package br.edu.iff.jogoforca.dominio.jogador;

import br.edu.iff.factory.EntityFactory;
import br.edu.iff.repository.Repository;
import br.edu.iff.repository.RepositoryException;

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
        if (soleInstance == null){
            throw new RuntimeException("Jogador Factory Implementation não incializada");
        }
        return soleInstance;
    }      
   
    private JogadorRepository getJogadorRepository(){
        return (JogadorRepository) this.repository;
    }

    @Override
    public Jogador getJogador(String nome) {
        Jogador novoJogador = Jogador.criar(getProximoId(), nome);        
        try {
            this.getJogadorRepository().inserir(novoJogador);
        } catch (RepositoryException e) {
            System.err.println(e.getMessage());
        }
        return  novoJogador;
    }
}
