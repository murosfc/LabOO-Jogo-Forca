package br.edu.iff.bancodepalavras.dominio.tema;

import br.edu.iff.factory.EntityFactory;
import br.edu.iff.repository.RepositoryException;

public class TemaFactoryImpl extends EntityFactory implements TemaFactory{
    private static TemaFactoryImpl soleInstance;   
    
    public static void createSoleInstance(TemaRepository repository) {
        if (soleInstance == null) {
            soleInstance = new TemaFactoryImpl(repository);
        }
    }
    
    public static TemaFactoryImpl getSoleInstance() {
        if (soleInstance == null){
            throw new RuntimeException("A Tema Factory Implementation ainda não foi iniciada");
        }
        return soleInstance;
    }

    private TemaFactoryImpl(TemaRepository repository) {
        super(repository);
    }
    
    private TemaRepository getTemaRepository(){
        return (TemaRepository) this.getRepository();
    }
    
    @Override
    public Tema getTema(String nome) {
        Tema novoTema = Tema.criar(getProximoId(), nome);
        try{
            this.getTemaRepository().inserir(novoTema);
        }catch (RepositoryException e){
            System.err.println(e.getMessage());
        }
        return novoTema;
    }   
   
}
