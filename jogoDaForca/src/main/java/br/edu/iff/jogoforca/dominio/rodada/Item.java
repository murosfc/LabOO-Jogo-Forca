package br.edu.iff.jogoforca.dominio.rodada;

import br.edu.iff.bancodepalavras.dominio.letra.Letra;
import br.edu.iff.bancodepalavras.dominio.palavra.Palavra;
import br.edu.iff.dominio.ObjetoDominioImpl;
import java.util.ArrayList;
import java.util.List;

public class Item extends ObjetoDominioImpl {

    private List<Boolean> posicoesDescobertas;
    private String palavraArriscada = null;
    private Palavra palavra;

    static Item criar(Long id, Palavra palavra) {
        return new Item(id, palavra);
    }

    public static Item reconstruir(Long id, Palavra palavra, List<Boolean> posicoesDescobertas, String palavraArriscada) {
        return new Item(id, palavra, posicoesDescobertas, palavraArriscada);
    }

    private Item(Long id, Palavra palavra) {
        super(id);
        this.palavra = palavra;       
        this.posicoesDescobertas = new ArrayList<Boolean>();
        for(int i = 0; i< palavra.getLetras().size(); i++)
            posicoesDescobertas.add(Boolean.FALSE);      
    }

    private Item(Long id, Palavra palavra, List<Boolean> posicoesDescobertas, String palavraArriscada) {
        super(id);
        this.posicoesDescobertas = posicoesDescobertas;
        this.palavra = palavra;
        this.palavraArriscada = palavraArriscada;
    }

    public Palavra getPalavra() {
        return palavra;
    }

    public List<Letra> getLetrasDescobertas() {
        List<Letra> descobertas = new ArrayList();
        for (int i = 0; i < this.palavra.getTamanho(); i++) {
            if (this.posicoesDescobertas.get(i)){
                descobertas.add(this.palavra.getLetra(i));
            }
        }
        return descobertas;
    }

    public List<Letra> getLetrasEncobertas() {
        List<Letra> encobertas = new ArrayList();
        for (int i = 0; i < this.palavra.getTamanho(); i++) {
            if (!this.posicoesDescobertas.get(i)){
                encobertas.add(this.palavra.getLetra(i));
            }
        }
        return encobertas;
    }  

    public int quantidadeLetrasEncobertas() {
        return this.getLetrasEncobertas().size();
    }

    public int calcularPontosLetrasEncobertas(int valorPorLetraEncoberta){
        return this.quantidadeLetrasEncobertas() * valorPorLetraEncoberta;
    }
    
    public boolean descobriu(){     
        return this.acertou() || this.quantidadeLetrasEncobertas() == 0;
    }
    
    public void exibir(Object context){
        List<Letra> letras = this.palavra.getLetras();
        for (Letra l : letras){
            l.equals(context);
        }       
    }
    
    List<Integer> tentar(char codigo){
        List<Integer> posicoesNaPalavra = new ArrayList<>();
        for (int i = 0; i < palavra.getTamanho(); i++){
            if (palavra.getLetra(i).equals(codigo)){
                posicoesNaPalavra.add(i);
                this.posicoesDescobertas.set(i, true);
            }
        }
        return posicoesNaPalavra;
    }
    
    void arriscar(String palavra){
        if (this.arriscou()){
            System.err.println("Só é permitido arriscar uma vez");
        }else{
            this.palavraArriscada = palavra;
        }
    }

    public String getPalavraArriscada() {
        return palavraArriscada;
    }
    
    public boolean arriscou(){
        return palavraArriscada != null;
    }
    
    public boolean acertou(){        
        return palavraArriscada == null ? false : this.palavra.comparar(palavraArriscada);
    }
   
}
