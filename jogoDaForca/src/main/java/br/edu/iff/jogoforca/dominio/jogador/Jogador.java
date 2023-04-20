package br.edu.iff.jogoforca.dominio.jogador;

import br.edu.iff.dominio.ObjetoDominioImpl;

public class Jogador extends ObjetoDominioImpl {
    private String nome;
    private int pontuacao = 0;

    public static Jogador criar(Long id,String nome){
        return new Jogador (id, nome);
    }

    public static Jogador reconstruir(Long id, String nome, int pontuacao){
        return new Jogador (id, nome, pontuacao);
    }

    private Jogador(Long id,String nome) {
        super(id);
        this.nome = nome;
    }
    
    private Jogador(Long id,String nome, int pontuacao) {
        super(id);
        this.nome = nome;
        this.pontuacao = pontuacao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(int pontuacao) {
        this.pontuacao += pontuacao;
    }
}
