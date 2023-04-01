package br.edu.iff.jogoforca.dominio.rodada.sorteio;

import br.edu.iff.bancodepalavras.dominio.palavra.Palavra;
import br.edu.iff.bancodepalavras.dominio.palavra.PalavraRepository;
import br.edu.iff.bancodepalavras.dominio.tema.Tema;
import br.edu.iff.bancodepalavras.dominio.tema.TemaRepository;
import br.edu.iff.jogoforca.dominio.jogador.Jogador;
import br.edu.iff.jogoforca.dominio.rodada.Rodada;
import br.edu.iff.jogoforca.dominio.rodada.RodadaFactoryImpl;
import br.edu.iff.jogoforca.dominio.rodada.RodadaRepository;
import br.edu.iff.repository.RepositoryException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RodadaSorteioFactory extends RodadaFactoryImpl{
    private static RodadaSorteioFactory soleInstance = null;    
   
    @Override
    public Rodada getRodada(Jogador jogador) {
        Random random = new Random();
        
        List<Tema> temasDisponíveis = getTemaRepository().getTodos();
        Tema temaSorteado = temasDisponíveis.get(random.nextInt(temasDisponíveis.size()));
        
        int quantidadeDePalavras = random.nextInt(Rodada.getMaxPalavras() + 1);
        
        List<Palavra> palavrasDoTemaSorteado = getPalavraRepository().getPorTema(temaSorteado);                
        List<Palavra> palavrasSorteadas = new ArrayList<>();
        
        if (quantidadeDePalavras >= palavrasDoTemaSorteado.size()){
            throw new RuntimeException("Não há palavras do tema " + temaSorteado + " no banco de palavras para iniciar uma rodada");
        }
        
        while (palavrasSorteadas.size() < quantidadeDePalavras){
            Palavra palavra = palavrasDoTemaSorteado.get(random.nextInt(palavrasDoTemaSorteado.size()));
            
            if(! palavrasSorteadas.contains(palavra)){
                palavrasSorteadas.add(palavra);
            }
        }
        
        Rodada rodada = Rodada.criar(getProximoId(), palavrasSorteadas, jogador);
        
        try{
            getRodadaRepository().inserir(rodada);
        }catch (RepositoryException exception) {
      exception.printStackTrace();
    }
        
        return rodada;
    }    
    
    public static void createSoleInstance(RodadaRepository rodadaRepository, TemaRepository temaRepository, PalavraRepository palavraRepository) {
        soleInstance = new RodadaSorteioFactory(rodadaRepository, temaRepository, palavraRepository);
    }    

    private RodadaSorteioFactory(RodadaRepository rodadaRepository, TemaRepository temaRepository, PalavraRepository palavraRepository) {
        super(rodadaRepository, temaRepository, palavraRepository);
    }

    public static RodadaSorteioFactory getSoleInstance() {
        if (soleInstance == null){
            throw new RuntimeException("Necessária a inicialização da fábrica de sorteio");
        }
        return soleInstance;
    }     
}
