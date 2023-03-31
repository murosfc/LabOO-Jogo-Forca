package br.edu.iff.bancodepalavras.dominio.tema;

public class Tema {
    private String nome;

    public static Tema criar(long id, String nome) {
        return new Tema(id, nome);
    }
    
    public static Tema reconstituir(long id, String nome) {
        return new Tema(id, nome);
    }
    
    private Tema(long id, String nome) {
        this.nome = nome;
    }
    
    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }

}
