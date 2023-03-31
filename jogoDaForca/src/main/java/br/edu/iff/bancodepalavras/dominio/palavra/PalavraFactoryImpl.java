package br.edu.iff.bancodepalavras.dominio.palavra;

import br.edu.iff.bancodepalavras.dominio.tema.Tema;
import br.edu.iff.factory.EntityFactory;
import br.edu.iff.repository.RepositoryException;

public class PalavraFactoryImpl extends EntityFactory implements PalavraFactory {

    private static PalavraFactoryImpl soleInstance;    

    private PalavraFactoryImpl(PalavraRepository repository) {
        super(repository);        
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
        return (PalavraRepository) this.getRepository();
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
