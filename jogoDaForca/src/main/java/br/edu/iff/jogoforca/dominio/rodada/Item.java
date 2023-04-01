package br.edu.iff.jogoforca.dominio.rodada;

import br.edu.iff.bancodepalavras.dominio.letra.Letra;
import br.edu.iff.bancodepalavras.dominio.palavra.Palavra;
import br.edu.iff.bancodepalavras.dominio.tema.Tema;
import br.edu.iff.dominio.ObjetoDominioImpl;
import java.util.ArrayList;
import java.util.Collections;
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
        this.posicoesDescobertas = new ArrayList<Boolean>(palavra.getLetras().size());
        Collections.fill(posicoesDescobertas, Boolean.FALSE);
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
        return getLetrasEncobertasOuDescobertas(true);
    }

    public List<Letra> getLetrasEncobertas() {
        return getLetrasEncobertasOuDescobertas(false);
    }

    private List<Letra> getLetrasEncobertasOuDescobertas(Boolean descoberta) {
        List<Letra> letrasBuscadas = new ArrayList<>();
        for (int i = 0; i < this.palavra.getTamanho(); i++) {
            boolean invertValue = (descoberta) ? this.posicoesDescobertas.get(i) : !this.posicoesDescobertas.get(i);
            if (this.posicoesDescobertas.get(i)) {
                letrasBuscadas.add(this.palavra.getLetra(i));
            }
        }
        return letrasBuscadas;
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
        this.palavra.exibir(context, this.posicoesDescobertas);
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
        return this.palavra.comparar(palavraArriscada);
    }
   
}
