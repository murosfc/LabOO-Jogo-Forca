package br.edu.iff.bancodepalavras.dominio.tema;

import br.edu.iff.dominio.ObjetoDominioImpl;

public class Tema extends ObjetoDominioImpl{
    private String nome;

    public static Tema criar(Long id, String nome) {
        return new Tema(id, nome);
    }
    
    public static Tema reconstituir(Long id, String nome) {
        return new Tema(id, nome);
    }
    
    private Tema(Long id, String nome) {
        super(id);
        this.nome = nome;
    }
    
    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }

}
