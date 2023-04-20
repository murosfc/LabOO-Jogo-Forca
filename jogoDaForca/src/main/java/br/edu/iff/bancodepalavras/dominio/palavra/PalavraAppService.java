package br.edu.iff.bancodepalavras.dominio.palavra;

import br.edu.iff.bancodepalavras.dominio.tema.Tema;
import br.edu.iff.bancodepalavras.dominio.tema.TemaRepository;
import br.edu.iff.repository.RepositoryException;

public class PalavraAppService {
    private static PalavraAppService soleInstance;
    
    private final TemaRepository temaRepository;
    private final PalavraRepository palavraRepository;
    private final PalavraFactory palavraFactory;
    
    private PalavraAppService(TemaRepository temaRepository, PalavraRepository palavraRepository, PalavraFactory palavraFactory) {
        this.temaRepository = temaRepository;
        this.palavraRepository = palavraRepository;
        this.palavraFactory = palavraFactory;
    }
    
    public static void createSoleInstance(TemaRepository temaRepository, PalavraRepository palavraRepository, PalavraFactory palavraFactory) {
        soleInstance = new PalavraAppService(temaRepository, palavraRepository, palavraFactory);
    }
    
    public static PalavraAppService getSoleInstance() {
        if (soleInstance == null){
            throw new RuntimeException("PalavraAppService não iniciado");
        }
        return soleInstance;
    }
    
    public boolean novaPalavra(String palavra, long idTema) {
        try {
            Palavra palavraExistente = palavraRepository.getPalavra(palavra);
            if (palavraExistente != null) {
                return true;
            }
            
            Tema tema = temaRepository.getPorId(idTema);
            if (tema == null) {
                return false;
            }
            
            Palavra novaPalavra = palavraFactory.getPalavra(palavra, tema);
            palavraRepository.inserir(novaPalavra);
            return true;
        } catch (RepositoryException e) {
            e.printStackTrace();
            return false;
        }
    }
}
