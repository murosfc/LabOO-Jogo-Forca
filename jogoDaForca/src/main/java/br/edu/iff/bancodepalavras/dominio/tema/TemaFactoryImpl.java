package br.edu.iff.bancodepalavras.dominio.tema;

import br.edu.iff.factory.EntityFactory;

public class TemaFactoryImpl extends EntityFactory implements TemaFactory{
    private static TemaFactoryImpl soleInstance;    

    private TemaFactoryImpl(TemaRepository repository) {
        super(repository);
    }

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

    @Override
    public Tema getTema(String nome) {
        return Tema.criar(getProximoId(), nome);
    }
    
    private TemaRepository getTemaRepository(){
        return (TemaRepository) this.getRepository();
    }

    
}
