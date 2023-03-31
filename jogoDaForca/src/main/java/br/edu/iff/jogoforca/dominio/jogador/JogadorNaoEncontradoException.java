package br.edu.iff.jogoforca.dominio.jogador;

public class JogadorNaoEncontradoException extends Exception{
    private String jogador;

    public String getJogador() {
        return jogador;
    }
    
    public JogadorNaoEncontradoException(String jogador){
        super(jogador);
    }
}
