package br.edu.iff.jogoforca.dominio.jogador.emmemoria;

public class MemoriaJogadorRepository {

    private static MemoriaJogadorRepository soleInstance;

    private MemoriaJogadorRepository(){}

    public MemoriaJogadorRepository getSoleInstance(){
        return soleInstance;
    }
}
